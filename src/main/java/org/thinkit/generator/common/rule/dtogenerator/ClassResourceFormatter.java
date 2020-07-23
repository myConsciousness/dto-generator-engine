/**
 * Project Name : generator-commons<br>
 * File Name : ClassResourceFormatter.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/11<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.rule.dtogenerator;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.flogger.FluentLogger;

import org.thinkit.common.catalog.Extension;
import org.thinkit.common.rule.AbstractRule;
import org.thinkit.generator.common.dto.dtogenerator.ClassCreatorDefinition;
import org.thinkit.generator.common.dto.dtogenerator.ClassDefinition;
import org.thinkit.generator.common.dto.dtogenerator.ClassDefinitionMatrix;
import org.thinkit.generator.common.dto.dtogenerator.ClassItemDefinition;
import org.thinkit.generator.common.dto.dtogenerator.ClassNameDefinition;
import org.thinkit.generator.common.dto.dtogenerator.ClassResource;
import org.thinkit.generator.common.factory.dtogenerator.DtoResourceFactory;
import org.thinkit.generator.common.factory.resource.ClassDescription;
import org.thinkit.generator.common.factory.resource.Constructor;
import org.thinkit.generator.common.factory.resource.Copyright;
import org.thinkit.generator.common.factory.resource.Resource;
import org.thinkit.generator.common.factory.resource.ResourceFactory;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * クラス定義マトリクス情報を基にJavaのDTOリソースを生成する処理を定義したルールクラスです。<br>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class ClassResourceFormatter extends AbstractRule {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * クラス定義情報群
     */
    @NonNull
    private ClassDefinitionMatrix classDefinitionMatrix = null;

    /**
     * クラスリソース
     */
    @Getter
    private ClassResource classResource = null;

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private ClassResourceFormatter() {
    }

    /**
     * コンストラクタ
     *
     * @param classDefinitionMatrix クラス定義情報群
     */
    public ClassResourceFormatter(final ClassDefinitionMatrix classDefinitionMatrix) {
        super();
        this.classDefinitionMatrix = classDefinitionMatrix;
    }

    @Override
    public boolean execute() {

        final ClassDefinitionMatrix classDefinitionMatrix = this.classDefinitionMatrix;
        final ClassNameDefinition classNameDefinition = classDefinitionMatrix.getClassNameDefinition();
        final Map<String, String> formattedResources = new HashMap<>();

        final RecursiveRequiredParameters parameters = RecursiveRequiredParameters.of(classNameDefinition,
                classDefinitionMatrix.getClassCreatorDefinition(), classDefinitionMatrix.getClassDefinitionList(),
                formattedResources);

        if (!this.formatClassDefinitionRecursively(parameters)) {
            logger.atSevere().log("クラス定義情報の整形処理が異常終了しました。");
            return false;
        }

        this.classResource = new ClassResource(classNameDefinition.getPackageName(), formattedResources);

        return true;
    }

    /**
     * 再帰的にクラス定義情報をJavaファイルへ出力する形式へ整形します。
     *
     * @param parameters 再帰処理に必要な情報を格納したデータクラス
     * @return 整形処理が正常終了した場合は{@code true}、それ以外は{@code false}
     * @see RecursiveRequiredParameters
     */
    private boolean formatClassDefinitionRecursively(@NonNull final RecursiveRequiredParameters parameters) {
        logger.atInfo().log("再帰処理に使用するパラメータ情報 = (%s)", parameters);

        final ClassNameDefinition classNameDefinition = parameters.getClassNameDefinition();
        final ClassCreatorDefinition classCreatorDefinition = parameters.getClassCreatorDefinition();
        final List<ClassDefinition> classDefinitionList = parameters.getClassDefinitionList();
        final Map<String, String> formattedResources = parameters.getFormattedResources();

        for (ClassDefinition classDefinition : classDefinitionList) {
            final String className = classDefinition.getClassName();
            final Resource resource = this.formatResource(className, classDefinition.getClassItemDefinitionList(),
                    classNameDefinition, classCreatorDefinition, formattedResources);

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
     * 各項目に子クラスが存在する場合は再帰処理({@link #formatClassDefinitionRecursively(RecursiveRequiredParameters)})を行います。<br>
     * <br>
     * 再帰処理中に想定外のエラーが発生した場合は必ず{@code null}を返却します。
     *
     * @param className               クラス名
     * @param classItemDefinitionList クラス項目定義情報リスト
     * @param classNameDefinition     クラス名定義情報
     * @param classCreatorDefinition  クラス作成者情報
     * @param formattedResources      整形されたJavaリソース情報
     * @return #see {@link Resource}
     */
    private Resource formatResource(@NonNull final String className,
            @NonNull final List<ClassItemDefinition> classItemDefinitionList,
            @NonNull final ClassNameDefinition classNameDefinition,
            @NonNull final ClassCreatorDefinition classCreatorDefinition,
            @NonNull final Map<String, String> formattedResources) {

        assert className.length() > 0;
        assert !classItemDefinitionList.isEmpty();

        final ResourceFactory resourceFactory = DtoResourceFactory.getInstance();
        final Resource resource = this.createResource(className, classNameDefinition, classCreatorDefinition);
        final Constructor requiredConstructor = this.createConstructor(className, "Constructor");
        final Constructor copyingConstructor = this.createConstructor(className, "Copying constructor");

        copyingConstructor.add(resourceFactory.createParameter(className, this.toInitialLowerCase(className)));

        for (ClassItemDefinition classItemDefinition : classItemDefinitionList) {
            final String dataType = classItemDefinition.getDataType();
            final String variableName = classItemDefinition.getVariableName();

            resource.add(resourceFactory.createDescription(classItemDefinition.getDescription()));
            resource.add(resourceFactory.createFieldDefinition(dataType, variableName,
                    classItemDefinition.getInitialValue()));

            if (classItemDefinition.isInvariant()) {
                requiredConstructor.add(resourceFactory.createFunctionParamAnnotation(variableName,
                        classItemDefinition.getDescription()));
                requiredConstructor.add(resourceFactory.createParameter(dataType, variableName));
                requiredConstructor.add(resourceFactory.createConstructorProcess(variableName).toRequired());
            }

            copyingConstructor.add(resourceFactory.createConstructorProcess(className, variableName).toCopying());

            final List<ClassDefinition> childClassDefinitionList = classItemDefinition.getChildClassDefinitionList();

            if (!childClassDefinitionList.isEmpty()) {
                logger.atInfo().log("子クラスが存在するため再帰処理を開始します。");

                final RecursiveRequiredParameters newRequiredParameters = RecursiveRequiredParameters
                        .of(classNameDefinition, classCreatorDefinition, childClassDefinitionList, formattedResources);

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
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。<br>
     *
     * @param className              クラス名
     * @param classNameDefinition    クラス名定義情報
     * @param classCreatorDefinition クラス作成者情報
     * @return 著作権定義オブジェクト
     *
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    private Copyright createCopyright(@NonNull String className, @NonNull ClassNameDefinition classNameDefinition,
            @NonNull ClassCreatorDefinition classCreatorDefinition) {
        return DtoResourceFactory.getInstance().createCopyright(classNameDefinition.getProjectName(),
                className + Extension.java(), StandardCharsets.UTF_8.name(), classCreatorDefinition.getCreator(),
                classCreatorDefinition.getCreationDate());
    }

    /**
     * 引数として渡された情報を基にリソース定義オブジェクトを生成し返却します。<br>
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。<br>
     *
     * @param className              クラス名
     * @param classNameDefinition    クラス名定義情報
     * @param classCreatorDefinition クラス作成者情報
     * @return リソース定義オブジェクト
     *
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    private Resource createResource(@NonNull String className, @NonNull ClassNameDefinition classNameDefinition,
            @NonNull ClassCreatorDefinition classCreatorDefinition) {

        final ResourceFactory resourceFactory = DtoResourceFactory.getInstance();
        final Copyright copyright = this.createCopyright(className, classNameDefinition, classCreatorDefinition);
        final ClassDescription classDescription = resourceFactory.createClassDescription(
                classNameDefinition.getDescription(), classCreatorDefinition.getCreator(),
                classNameDefinition.getVersion());

        return resourceFactory.createResource(copyright, classNameDefinition.getPackageName(), classDescription,
                className);
    }

    /**
     * 引数として渡された情報を基にコンストラクタ定義オブジェクトを生成し返却します。<br>
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。<br>
     *
     * @param className   クラス名
     * @param description 説明
     * @return コンストラクタ定義オブジェクト
     *
     * @exception NullPointerException 引数として{@code null}が渡された場合
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
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    private String toInitialLowerCase(@NonNull String sequence) {
        return sequence.substring(0, 1).toLowerCase() + sequence.substring(1);
    }

    /**
     * 整形時の再帰処理で使用する情報を管理するデータクラスです。 <br>
     * インスタンス成時に各フィールドへnullが設定された場合は必ず例外が発生します。
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
         * クラス名定義情報
         */
        @NonNull
        private final ClassNameDefinition classNameDefinition;

        /**
         * クラス作成者情報
         */
        @NonNull
        private final ClassCreatorDefinition classCreatorDefinition;

        /**
         * クラス定義情報群
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
