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

package org.thinkit.generator.common.command.dtogenerator;

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
import org.thinkit.generator.common.vo.dto.ClassCreator;
import org.thinkit.generator.common.vo.dto.ClassDefinition;
import org.thinkit.generator.common.vo.dto.ClassDefinitionMatrix;
import org.thinkit.generator.common.vo.dto.ClassItem;
import org.thinkit.generator.common.vo.dto.ClassMeta;
import org.thinkit.generator.common.vo.dto.ClassResource;

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
public final class DtoResourceFormatter implements Command<ClassResource> {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * クラス定義マトリクス
     */
    @NonNull
    private ClassDefinitionMatrix classDefinitionMatrix;

    /**
     * デフォルトコンストラクタ
     */
    private DtoResourceFormatter() {
    }

    /**
     * コンストラクタ
     *
     * @param classDefinitionMatrix クラス定義マトリクス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoResourceFormatter(@NonNull final ClassDefinitionMatrix classDefinitionMatrix) {
        this.classDefinitionMatrix = classDefinitionMatrix;
    }

    /**
     * 引数として渡された {@code classDefinitionMatrix} を基に {@link DtoResourceFormatter}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param classDefinitionMatrix クラス定義マトリクス
     * @return {@link DtoResourceFormatter} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public Command<ClassResource> of(@NonNull final ClassDefinitionMatrix classDefinitionMatrix) {
        return new DtoResourceFormatter(classDefinitionMatrix);
    }

    @Override
    public ClassResource run() {

        final ClassDefinitionMatrix classDefinitionMatrix = this.classDefinitionMatrix;
        final ClassMeta classMeta = classDefinitionMatrix.getClassMeta();
        final Map<String, String> formattedResources = Map.of();

        final RecursiveRequiredParameters parameters = RecursiveRequiredParameters.of(classMeta,
                classDefinitionMatrix.getClassCreator(), classDefinitionMatrix.getClassDefinitionList(),
                formattedResources);

        if (!this.formatClassDefinitionRecursively(parameters)) {
            logger.atSevere().log("クラス定義情報の整形処理が異常終了しました。");
            return null;
        }

        return new ClassResource(classMeta.getPackageName(), formattedResources);
    }

    /**
     * 再帰的にクラス定義情報をJavaファイルへ出力する形式へ整形します。
     *
     * @param parameters 再帰処理に必要な情報を格納したデータクラス
     * @return 整形処理が正常終了した場合は {@code true}、それ以外は {@code false}
     *
     * @see RecursiveRequiredParameters
     */
    private boolean formatClassDefinitionRecursively(@NonNull final RecursiveRequiredParameters parameters) {
        logger.atInfo().log("再帰処理に使用するパラメータ情報 = (%s)", parameters);

        final ClassMeta classMeta = parameters.getClassMeta();
        final ClassCreator classCreator = parameters.getClassCreator();
        final List<ClassDefinition> classDefinitionList = parameters.getClassDefinitionList();
        final Map<String, String> formattedResources = parameters.getFormattedResources();

        for (ClassDefinition classDefinition : classDefinitionList) {
            final String className = classDefinition.getClassName();
            final Resource resource = this.formatResource(className, classDefinition.getClassItemList(), classMeta,
                    classCreator, formattedResources);

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
     * {@link #formatClassDefinitionRecursively(RecursiveRequiredParameters)}
     * )を行います。
     * <p>
     * 再帰処理中に想定外のエラーが発生した場合は必ず {@code null} を返却します。
     *
     * @param className          クラス名
     * @param classItemList      クラス項目定義情報リスト
     * @param classMeta          クラスメタ
     * @param classCreator       クラス作成者
     * @param formattedResources 整形されたJavaリソース情報
     *
     * @return #see {@link Resource}
     */
    private Resource formatResource(@NonNull final String className, @NonNull final List<ClassItem> classItemList,
            @NonNull final ClassMeta classMeta, @NonNull final ClassCreator classCreator,
            @NonNull final Map<String, String> formattedResources) {

        assert className.length() > 0;
        assert !classItemList.isEmpty();

        final ResourceFactory resourceFactory = DtoResourceFactory.getInstance();
        final Resource resource = this.createResource(className, classMeta, classCreator);
        final Constructor requiredConstructor = this.createConstructor(className, "Constructor");
        final Constructor copyingConstructor = this.createConstructor(className, "Copying constructor");

        copyingConstructor.add(resourceFactory.createParameter(className, this.toInitialLowerCase(className)));

        for (ClassItem classItem : classItemList) {
            final String dataType = classItem.getDataType();
            final String variableName = classItem.getVariableName();

            resource.add(resourceFactory.createDescription(classItem.getDescription()));
            resource.add(resourceFactory.createFieldDefinition(dataType, variableName, classItem.getInitialValue()));

            if (classItem.isInvariant()) {
                requiredConstructor
                        .add(resourceFactory.createFunctionParamAnnotation(variableName, classItem.getDescription()));
                requiredConstructor.add(resourceFactory.createParameter(dataType, variableName));
                requiredConstructor.add(resourceFactory.createConstructorProcess(variableName).toRequired());
            }

            copyingConstructor.add(resourceFactory.createConstructorProcess(className, variableName).toCopying());

            final List<ClassDefinition> childClassDefinitionList = classItem.getChildClassDefinitionList();

            if (!childClassDefinitionList.isEmpty()) {
                logger.atInfo().log("子クラスが存在するため再帰処理を開始します。");

                final RecursiveRequiredParameters newRequiredParameters = RecursiveRequiredParameters.of(classMeta,
                        classCreator, childClassDefinitionList, formattedResources);

                if (!this.formatClassDefinitionRecursively(newRequiredParameters)) {
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
     * @param className    クラス名
     * @param classMeta    クラスメタ
     * @param classCreator クラス作成者
     * @return 著作権定義オブジェクト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private Copyright createCopyright(@NonNull String className, @NonNull ClassMeta classMeta,
            @NonNull ClassCreator classCreator) {
        return DtoResourceFactory.getInstance().createCopyright(classMeta.getProjectName(),
                className + Extension.java(), StandardCharsets.UTF_8.name(), classCreator.getCreator(),
                classCreator.getCreationDate());
    }

    /**
     * 引数として渡された情報を基にリソース定義オブジェクトを生成し返却します。<br>
     * 引数として {@code null} が渡された場合は実行時に必ず失敗します。<br>
     *
     * @param className    クラス名
     * @param classMeta    クラスメタ
     * @param classCreator クラス作成者
     * @return リソース定義オブジェクト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private Resource createResource(@NonNull String className, @NonNull ClassMeta classMeta,
            @NonNull ClassCreator classCreator) {

        final ResourceFactory resourceFactory = DtoResourceFactory.getInstance();
        final Copyright copyright = this.createCopyright(className, classMeta, classCreator);
        final ClassDescription classDescription = resourceFactory.createClassDescription(classMeta.getDescription(),
                classCreator.getCreator(), classMeta.getVersion());

        return resourceFactory.createResource(copyright, classMeta.getPackageName(), classDescription, className);
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
        private final ClassMeta classMeta;

        /**
         * クラス作成者
         */
        @NonNull
        private final ClassCreator classCreator;

        /**
         * クラス定義マトリクス
         */
        @NonNull
        private final List<ClassDefinition> classDefinitionList;

        /**
         * 整形済みJavaリソース
         */
        @NonNull
        private final Map<String, String> formattedResources;
    }
}
