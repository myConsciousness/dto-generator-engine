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

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import com.google.common.flogger.FluentLogger;

import org.thinkit.common.catalog.Extension;
import org.thinkit.common.command.Command;
import org.thinkit.generator.common.factory.dtogenerator.DtoResourceFactory;
import org.thinkit.generator.common.factory.resource.ClassDescription;
import org.thinkit.generator.common.factory.resource.Constructor;
import org.thinkit.generator.common.factory.resource.Copyright;
import org.thinkit.generator.common.factory.resource.Resource;
import org.thinkit.generator.common.factory.resource.ResourceFactory;
import org.thinkit.generator.common.vo.dto.DtoCreator;
import org.thinkit.generator.common.vo.dto.DtoDefinition;
import org.thinkit.generator.common.vo.dto.DtoDefinitionMatrix;
import org.thinkit.generator.common.vo.dto.DtoField;
import org.thinkit.generator.common.vo.dto.DtoMeta;
import org.thinkit.generator.common.vo.dto.DtoResource;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * クラス定義マトリクス情報を基にJavaのDTOリソースを生成する処理を定義したコマンドクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class DtoResourceFormatter implements Command<DtoResource> {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * クラス定義マトリクス
     */
    @NonNull
    private DtoDefinitionMatrix dtoDefinitionMatrix;

    /**
     * デフォルトコンストラクタ
     */
    private DtoResourceFormatter() {
    }

    /**
     * コンストラクタ
     *
     * @param dtoDefinitionMatrix クラス定義マトリクス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoResourceFormatter(@NonNull final DtoDefinitionMatrix dtoDefinitionMatrix) {
        this.dtoDefinitionMatrix = dtoDefinitionMatrix;
    }

    /**
     * 引数として渡された {@code dtoDefinitionMatrix} を基に {@link DtoResourceFormatter}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param dtoDefinitionMatrix クラス定義マトリクス
     * @return {@link DtoResourceFormatter} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static Command<DtoResource> of(@NonNull final DtoDefinitionMatrix dtoDefinitionMatrix) {
        return new DtoResourceFormatter(dtoDefinitionMatrix);
    }

    @Override
    public DtoResource run() {

        final DtoDefinitionMatrix dtoDefinitionMatrix = this.dtoDefinitionMatrix;
        final DtoMeta dtoMeta = dtoDefinitionMatrix.getDtoMeta();
        final Map<String, String> formattedResources = Map.of();

        final RecursiveRequiredParameters parameters = RecursiveRequiredParameters.of(dtoMeta,
                dtoDefinitionMatrix.getDtoCreator(), dtoDefinitionMatrix.getDtoDefinitionList(), formattedResources);

        if (!this.formatDtoDefinitionRecursively(parameters)) {
            logger.atSevere().log("クラス定義情報の整形処理が異常終了しました。");
            return null;
        }

        return new DtoResource(dtoMeta.getPackageName(), formattedResources);
    }

    /**
     * 再帰的にクラス定義情報をJavaファイルへ出力する形式へ整形します。
     *
     * @param parameters 再帰処理に必要な情報を格納したデータクラス
     * @return 整形処理が正常終了した場合は {@code true}、それ以外は {@code false}
     *
     * @see RecursiveRequiredParameters
     */
    private boolean formatDtoDefinitionRecursively(@NonNull final RecursiveRequiredParameters parameters) {
        logger.atInfo().log("再帰処理に使用するパラメータ情報 = (%s)", parameters);

        final DtoMeta dtoMeta = parameters.getDtoMeta();
        final DtoCreator dtoCreator = parameters.getDtoCreator();
        final List<DtoDefinition> dtoDefinitionList = parameters.getDtoDefinitionList();
        final Map<String, String> formattedResources = parameters.getFormattedResources();

        for (DtoDefinition dtoDefinition : dtoDefinitionList) {
            final String className = dtoDefinition.getClassName();
            final Resource resource = this.formatResource(className, dtoDefinition.getDtoFieldList(), dtoMeta,
                    dtoCreator, formattedResources);

            if (resource == null) {
                logger.atSevere().log("クラス項目定義情報の整形処理が異常終了しました。");
                return false;
            }

            formattedResources.put(className, resource.createResource());
        }

        logger.atInfo().log("整形されたJavaリソース = (%s)", formattedResources);
        return true;
    }

    /**
     * 引数として渡された情報を基にリソース情報を構築します。<br>
     * 各項目に子クラスが存在する場合は再帰処理(
     * {@link #formatDtoDefinitionRecursively(RecursiveRequiredParameters)} )を行います。
     * <p>
     * 再帰処理中に想定外のエラーが発生した場合は必ず {@code null} を返却します。
     *
     * @param className          クラス名
     * @param dtoFieldList       クラス項目定義情報リスト
     * @param dtoMeta            クラスメタ
     * @param dtoCreator         クラス作成者
     * @param formattedResources 整形されたJavaリソース情報
     *
     * @return #see {@link Resource}
     */
    private Resource formatResource(@NonNull final String className, @NonNull final List<DtoField> dtoFieldList,
            @NonNull final DtoMeta dtoMeta, @NonNull final DtoCreator dtoCreator,
            @NonNull final Map<String, String> formattedResources) {

        assert className.length() > 0;
        assert !dtoFieldList.isEmpty();

        final ResourceFactory resourceFactory = DtoResourceFactory.getInstance();
        final Resource resource = this.createResource(className, dtoMeta, dtoCreator);
        final Constructor requiredConstructor = this.createConstructor(className, "Constructor");
        final Constructor copyingConstructor = this.createConstructor(className, "Copying constructor");

        copyingConstructor.add(resourceFactory.createParameter(className, this.toInitialLowerCase(className)));

        for (DtoField dtoField : dtoFieldList) {
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

            final List<DtoDefinition> childDtoDefinitionList = dtoField.getChildDtoDefinitionList();

            if (!childDtoDefinitionList.isEmpty()) {
                logger.atInfo().log("子クラスが存在するため再帰処理を開始します。");

                final RecursiveRequiredParameters newRequiredParameters = RecursiveRequiredParameters.of(dtoMeta,
                        dtoCreator, childDtoDefinitionList, formattedResources);

                if (!this.formatDtoDefinitionRecursively(newRequiredParameters)) {
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
     * 引数として渡された情報を基に著作権定義オブジェクトを生成し返却します。<br>
     * 引数として {@code null} が渡された場合は実行時に必ず失敗します。<br>
     *
     * @param className  クラス名
     * @param dtoMeta    クラスメタ
     * @param dtoCreator クラス作成者
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
     * 引数として渡された情報を基にリソース定義オブジェクトを生成し返却します。<br>
     * 引数として {@code null} が渡された場合は実行時に必ず失敗します。<br>
     *
     * @param className  クラス名
     * @param dtoMeta    クラスメタ
     * @param dtoCreator クラス作成者
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
     * 引数として渡された情報を基にコンストラクタ定義オブジェクトを生成し返却します。<br>
     * 引数として {@code null} が渡された場合は実行時に必ず失敗します。<br>
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

    /**
     * 整形時の再帰処理で使用する情報を管理するデータクラスです。 <br>
     * インスタンス成時に各フィールドへ {@code null} が設定された場合は必ず例外が発生します。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Getter
    @ToString
    @EqualsAndHashCode(callSuper = false)
    @RequiredArgsConstructor(staticName = "of")
    private static class RecursiveRequiredParameters implements Serializable {

        /**
         * シリアルバージョンUID
         */
        private static final long serialVersionUID = -9084043880553539265L;

        /**
         * クラスメタ
         */
        @NonNull
        private final DtoMeta DtoMeta;

        /**
         * クラス作成者
         */
        @NonNull
        private final DtoCreator DtoCreator;

        /**
         * クラス定義マトリクス
         */
        @NonNull
        private final List<DtoDefinition> DtoDefinitionList;

        /**
         * 整形済みJavaリソース
         */
        @NonNull
        private final Map<String, String> formattedResources;
    }
}
