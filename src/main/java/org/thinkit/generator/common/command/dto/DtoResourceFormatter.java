/**
 * Project Name : generator-commons<br>
 * File Name : DtoResourceFormatter.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/11<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.command.dto;

import java.nio.charset.StandardCharsets;

import com.google.common.flogger.FluentLogger;

import org.thinkit.common.catalog.Extension;
import org.thinkit.common.command.Command;
import org.thinkit.formatter.JavaFormatter;
import org.thinkit.generator.common.factory.dtogenerator.DtoResourceFactory;
import org.thinkit.generator.common.factory.resource.ClassDescription;
import org.thinkit.generator.common.factory.resource.Constructor;
import org.thinkit.generator.common.factory.resource.Copyright;
import org.thinkit.generator.common.factory.resource.Resource;
import org.thinkit.generator.common.factory.resource.ResourceFactory;
import org.thinkit.generator.common.vo.dto.DtoCreator;
import org.thinkit.generator.common.vo.dto.DtoDefinition;
import org.thinkit.generator.common.vo.dto.DtoDefinitionGroup;
import org.thinkit.generator.common.vo.dto.DtoField;
import org.thinkit.generator.common.vo.dto.DtoFieldGroup;
import org.thinkit.generator.common.vo.dto.DtoMatrix;
import org.thinkit.generator.common.vo.dto.DtoMeta;
import org.thinkit.generator.common.vo.dto.DtoResource;
import org.thinkit.generator.common.vo.dto.DtoResourceGroup;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * DTOマトリクス情報を基にJavaのDTOリソースを生成する処理を定義したコマンドクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class DtoResourceFormatter implements Command<DtoResourceGroup> {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * DTOマトリクス
     */
    private DtoMatrix dtoMatrix;

    /**
     * デフォルトコンストラクタ
     */
    private DtoResourceFormatter() {
    }

    /**
     * コンストラクタ
     *
     * @param dtoMatrix DTOマトリクス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoResourceFormatter(@NonNull final DtoMatrix dtoMatrix) {
        this.dtoMatrix = dtoMatrix;
    }

    /**
     * 引数として渡された {@code dtoMatrix} を基に {@link DtoResourceFormatter}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param dtoMatrix DTOマトリクス
     * @return {@link DtoResourceFormatter} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static Command<DtoResourceGroup> of(@NonNull final DtoMatrix dtoMatrix) {
        return new DtoResourceFormatter(dtoMatrix);
    }

    @Override
    public DtoResourceGroup execute() {

        final DtoResourceGroup dtoResourceGroup = DtoResourceGroup.of();

        if (!this.formatDtoResourceRecursively(this.dtoMatrix.getDtoMeta(), this.dtoMatrix.getDtoCreator(),
                this.dtoMatrix.getDtoDefinitionGroup(), dtoResourceGroup)) {
            logger.atSevere().log("DTO定義情報の整形処理が異常終了しました。");
            return null;
        }

        logger.atFinest().log("DTOリソースグループ = (%s)", dtoResourceGroup);
        return dtoResourceGroup;
    }

    /**
     * 再帰的にDTOリソースをjavaファイルへ出力する形式へ整形する処理を定義したメソッドです。整形されたDTOリソース情報は引数として渡された
     * {@code dtoResourceGroup} オブジェクトに格納されます。
     *
     * @param dtoMeta            DTOメタ（入力）
     * @param dtoCreator         DTO作成者（入力）
     * @param dtoDefinitionGroup DTO定義グループ（入力）
     * @param dtoResourceGroup   DTOリソースグループ（出力）
     * @return 再帰処理が正常終了した場合は {@code true}、それ以外は {@code false}
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private boolean formatDtoResourceRecursively(@NonNull final DtoMeta dtoMeta, @NonNull final DtoCreator dtoCreator,
            @NonNull final DtoDefinitionGroup dtoDefinitionGroup, @NonNull final DtoResourceGroup dtoResourceGroup) {

        final JavaFormatter formatter = JavaFormatter.of();

        for (DtoDefinition dtoDefinition : dtoDefinitionGroup) {
            final String className = dtoDefinition.getClassName();
            final Resource resource = this.formatResource(className, dtoDefinition.getDtoFieldGroup(), dtoMeta,
                    dtoCreator, dtoResourceGroup);

            if (resource == null) {
                logger.atSevere().log("クラス項目定義情報の整形処理が異常終了しました。");
                return false;
            }

            dtoResourceGroup.add(
                    DtoResource.of(dtoMeta.getPackageName(), className, formatter.format(resource.createResource())));
        }

        return true;
    }

    /**
     * 引数として渡された情報を基にリソース情報を構築します。
     * <p>
     * 各項目に子クラスが存在する場合は再帰処理(
     * {@link #formatDtoResourceRecursively(DtoMeta, DtoCreator, DtoDefinitionGroup, DtoResourceGroup)}
     * )を行います。
     * <p>
     * 再帰処理中に想定外のエラーが発生した場合は必ず {@code null} を返却します。
     *
     * @param className        クラス名
     * @param dtoFieldGroup    DTOフィールドグループ
     * @param dtoMeta          DTOメタ
     * @param dtoCreator       DTO作成者
     * @param dtoResourceGroup DTOリソースグループ
     *
     * @return DTOリソースオブジェクト
     */
    private Resource formatResource(@NonNull final String className, @NonNull final DtoFieldGroup dtoFieldGroup,
            @NonNull final DtoMeta dtoMeta, @NonNull final DtoCreator dtoCreator,
            @NonNull final DtoResourceGroup dtoResourceGroup) {

        final ResourceFactory resourceFactory = DtoResourceFactory.getInstance();
        final Resource resource = this.createResource(className, dtoMeta, dtoCreator);
        final Constructor requiredConstructor = this.createConstructor(className, "Constructor");
        final Constructor copyingConstructor = this.createConstructor(className, "Copying constructor");

        copyingConstructor.add(resourceFactory.createParameter(className, this.toInitialLowerCase(className)));

        for (DtoField dtoField : dtoFieldGroup) {
            final String dataType = dtoField.getDataType();
            final String variableName = dtoField.getVariableName();

            resource.add(resourceFactory.createDescription(dtoField.getDescription()));
            resource.add(resourceFactory.createFieldDefinition(dataType, variableName, dtoField.getInitialValue()));

            if (dtoField.isInvariant()) {
                requiredConstructor
                        .add(resourceFactory.createFunctionParamAnnotation(variableName, dtoField.getDescription()));
                requiredConstructor.add(resourceFactory.createParameter(dataType, variableName));
                requiredConstructor.add(resourceFactory.createConstructorProcess(variableName).toRequired());
            }

            copyingConstructor.add(resourceFactory.createConstructorProcess(className, variableName).toCopying());

            final DtoDefinitionGroup childDtoDefinitionGroup = dtoField.getChildDtoDefinitionGroup();

            if (!childDtoDefinitionGroup.isEmpty()) {
                logger.atFinest().log("子クラスが存在するため再帰処理を開始します。");

                if (!this.formatDtoResourceRecursively(dtoMeta, dtoCreator, childDtoDefinitionGroup,
                        dtoResourceGroup)) {
                    logger.atSevere().log("子クラス定義情報を生成するための再起処理が異常終了しました。");
                    return null;
                }
            }
        }

        resource.add(requiredConstructor);
        resource.add(copyingConstructor);

        return resource;
    }

    /**
     * 引数として渡された情報を基に著作権定義オブジェクトを生成し返却します。
     *
     * @param className  クラス名
     * @param dtoMeta    DTOメタ
     * @param dtoCreator DTO作成者
     * @return 著作権定義オブジェクト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private Copyright createCopyright(@NonNull String className, @NonNull DtoMeta dtoMeta,
            @NonNull DtoCreator dtoCreator) {
        return DtoResourceFactory.getInstance().createCopyright(dtoMeta.getProjectName(), className + Extension.java(),
                StandardCharsets.UTF_8.name(), dtoCreator.getCreator(), dtoCreator.getCreationDate());
    }

    /**
     * 引数として渡された情報を基にリソース定義オブジェクトを生成し返却します。
     *
     * @param className  クラス名
     * @param dtoMeta    DTOメタ
     * @param dtoCreator DTO作成者
     * @return リソース定義オブジェクト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private Resource createResource(@NonNull String className, @NonNull DtoMeta dtoMeta,
            @NonNull DtoCreator dtoCreator) {

        final ResourceFactory resourceFactory = DtoResourceFactory.getInstance();
        final Copyright copyright = this.createCopyright(className, dtoMeta, dtoCreator);
        final ClassDescription classDescription = resourceFactory.createClassDescription(dtoMeta.getDescription(),
                dtoCreator.getCreator(), dtoMeta.getVersion());

        return resourceFactory.createResource(copyright, dtoMeta.getPackageName(), classDescription, className);
    }

    /**
     * 引数として渡された情報を基にコンストラクタ定義オブジェクトを生成し返却します。
     *
     * @param className   クラス名
     * @param description 説明
     * @return コンストラクタ定義オブジェクト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private Constructor createConstructor(@NonNull String className, @NonNull String description) {
        final ResourceFactory resourceFactory = DtoResourceFactory.getInstance();
        return resourceFactory.createConstructor(className, resourceFactory.createFunctionDescription(description));
    }

    /**
     * 文字列の上1桁目を小文字に変換して返却します。
     *
     * @param sequence 文字列
     * @return 上1桁目が小文字に変換された文字列
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private String toInitialLowerCase(@NonNull String sequence) {
        return sequence.substring(0, 1).toLowerCase() + sequence.substring(1);
    }
}
