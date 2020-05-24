/**
 * Project Name : Generator<br>
 * File Name : ClassDefinitionMatrixFormatter.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/11<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.dtogenerator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.thinkit.common.catalog.Delimiter;
import org.thinkit.common.catalog.EscapeSequence;
import org.thinkit.common.rule.AbstractRule;
import org.thinkit.generator.catalog.dtogenerator.DtoClassTemplate;
import org.thinkit.generator.catalog.dtogenerator.DtoTemplate;
import org.thinkit.generator.catalog.dtogenerator.DtoTemplateReplacementSequence;
import org.thinkit.generator.dtogenerator.ClassCreatorDefinition;
import org.thinkit.generator.dtogenerator.ClassDefinition;
import org.thinkit.generator.dtogenerator.ClassDefinitionMatrix;
import org.thinkit.generator.dtogenerator.ClassItemDefinition;
import org.thinkit.generator.dtogenerator.ClassNameDefinition;

import com.google.common.flogger.FluentLogger;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * {@link ClassDefinitionMatrixManager}から取得したクラス定義マトリクス情報を基に<br>
 * JavaのDTOリソースを生成する処理を定義したルールクラスです。<br>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class ClassDefinitionMatrixFormatter extends AbstractRule {

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
         * 整形されたJavaリソースリスト
         */
        @Getter
        private Map<String, String> formattedResources = new HashMap<>(0);

        /**
         * フィールド定義
         */
        @Getter(AccessLevel.PRIVATE)
        private StringBuilder fieldsDefinition = new StringBuilder();

        /**
         * コンストラクタParamアノテーション情報
         */
        @Getter(AccessLevel.PRIVATE)
        private StringBuilder constructorParamAnnotations = new StringBuilder();

        /**
         * コンストラクタ引数情報
         */
        @Getter(AccessLevel.PRIVATE)
        private StringBuilder constructorParameters = new StringBuilder();

        /**
         * コンストラクタ処理情報
         */
        @Getter(AccessLevel.PRIVATE)
        private StringBuilder constructorProcess = new StringBuilder();

        /**
         * コピーコンストラクタ処理情報
         */
        @Getter(AccessLevel.PRIVATE)
        private StringBuilder copyingConstructorProcess = new StringBuilder();

        /**
         * デフォルトコンストラクタ
         */
        @SuppressWarnings("unused")
        private ClassDefinitionMatrixFormatter() {
        }

        /**
         * コンストラクタ
         *
         * @param classDefinitionMatrix クラス定義情報群
         */
        public ClassDefinitionMatrixFormatter(final ClassDefinitionMatrix classDefinitionMatrix) {
                super();
                this.classDefinitionMatrix = classDefinitionMatrix;
        }

        @Override
        public boolean execute() {
                logger.atInfo().log("START");

                final DtoTemplateManager dtoTemplateManager = new DtoTemplateManager();

                if (!dtoTemplateManager.execute()) {
                        logger.atSevere().log("DTO雛形情報の取得処理が異常終了しました。");
                        return false;
                }

                final DtoTemplateReplacementSequenceManager dtoTemplateReplacementSequenceManager = new DtoTemplateReplacementSequenceManager();

                if (!dtoTemplateReplacementSequenceManager.execute()) {
                        logger.atSevere().log("DTO雛形置換文字列情報の取得処理が異常終了しました。");
                        return false;
                }

                final ClassDefinitionMatrix classDefinitionMatrix = this.classDefinitionMatrix;
                final Map<String, String> formattedResources = new HashMap<>();

                final RecursiveRequiredParameters parameters = RecursiveRequiredParameters.of(dtoTemplateManager,
                                dtoTemplateReplacementSequenceManager, classDefinitionMatrix.getClassNameDefinition(),
                                classDefinitionMatrix.getClassCreatorDefinition(),
                                classDefinitionMatrix.getClassDefinitionList(), formattedResources);

                if (!this.formatClassDefinitionRecursively(parameters)) {
                        logger.atSevere().log("クラス定義情報の整形処理が異常終了しました。");
                        return false;
                }

                this.formattedResources = formattedResources;

                logger.atInfo().log("整形されたJavaリソースマップ = (%s)", formattedResources);
                logger.atInfo().log("END");
                return true;
        }

        /**
         * 再帰的にクラス定義情報をJavaファイルへ出力する形式へ整形します。
         *
         * @param parameters 再帰処理に必要な情報を格納したデータクラス
         * @return 整形処理が正常終了した場合は{@code true}、それ以外は{@code false}
         * @see RecursiveRequiredParameters
         */
        private boolean formatClassDefinitionRecursively(final RecursiveRequiredParameters parameters) {
                logger.atInfo().log("START");
                logger.atInfo().log("再帰処理に使用するパラメータ情報 = (%s)", parameters);

                final DtoTemplateManager dtoTemplateManager = parameters.getDtoTemplateManager();
                final DtoTemplateReplacementSequenceManager dtoTemplateReplacementSequenceManager = parameters
                                .getDtoTemplateReplacementSequences();
                final ClassNameDefinition classNameDefinition = parameters.getClassNameDefinition();
                final ClassCreatorDefinition classCreatorDefinition = parameters.getClassCreatorDefinition();
                final List<ClassDefinition> classDefinitionList = parameters.getClassDefinitionList();
                final Map<String, String> formattedResources = parameters.getFormattedResources();

                for (ClassDefinition classDefinition : classDefinitionList) {

                        final List<ClassItemDefinition> classItemDefinitionList = classDefinition
                                        .getClassItemDefinitionList();
                        final String className = classDefinition.getClassName();

                        final DtoClassTemplate dtoClassTemplateCategory = this.organizeClassItemDefinition(
                                        classItemDefinitionList, className, classNameDefinition, classCreatorDefinition,
                                        dtoTemplateManager, dtoTemplateReplacementSequenceManager, formattedResources);

                        if (dtoClassTemplateCategory == null) {
                                logger.atSevere().log("クラス項目定義情報の構築処理が異常終了しました。");
                                return false;
                        }

                        final DtoClassTemplateManager dtoClassTemplateManager = new DtoClassTemplateManager(
                                        dtoClassTemplateCategory);

                        if (!dtoClassTemplateManager.execute()) {
                                logger.atSevere().log("DTOクラス雛形情報の取得処理が異常終了しました。");
                                return false;
                        }

                        final String copyingConstructorParameter = this.getCopyingConstructorParameter(className,
                                        dtoTemplateManager, dtoTemplateReplacementSequenceManager);

                        final ResourceRequiredElements resourceRequiredElements = ResourceRequiredElements.of(
                                        dtoClassTemplateManager.getDtoClassTemplate(), classNameDefinition,
                                        classCreatorDefinition, classDefinition, dtoTemplateManager,
                                        dtoTemplateReplacementSequenceManager, this.getFieldsDefinition(),
                                        this.getConstructorParamAnnotations(), this.getConstructorParameters(),
                                        this.getConstructorProcess(), copyingConstructorParameter,
                                        this.getCopyingConstructorProcess());

                        final String resource = this.createResource(resourceRequiredElements);

                        formattedResources.put(className, resource);
                }

                logger.atInfo().log("整形されたJavaリソース = (%s)", formattedResources);
                logger.atInfo().log("END");
                return true;
        }

        /**
         * 引数として渡された情報を基にクラス項目定義に紐づく情報を構築します。<br>
         * 各項目に子クラスが存在する場合は再帰処理({@link #formatClassDefinitionRecursively(RecursiveRequiredParameters)})を行います。<br>
         * <br>
         * 構築処理の中で各項目の不変性を確認し、<br>
         * それぞれの状態に適応するDTOクラスの雛形区分({@link DtoClassTemplate})を返却します。<br>
         * <br>
         * 再帰処理中に想定外のエラーが発生した場合は必ず{@code <code>null</code>}を返却します。
         *
         * @param classItemDefinitionList               クラス項目定義情報リスト
         * @param className                             クラス名
         * @param classNameDefinition                   クラス名定義情報
         * @param classCreatorDefinition                クラス作成者情報
         * @param dtoTemplateManager                    DTOの雛形情報を管理するルールクラス
         * @param dtoTemplateReplacementSequenceManager DTOの雛形置換文字列情報を管理するルールクラス
         * @param formattedResources                    整形されたJavaリソース情報
         * @return #see
         *         {@link #formatClassDefinitionRecursively(RecursiveRequiredParameters)}
         */
        private DtoClassTemplate organizeClassItemDefinition(final List<ClassItemDefinition> classItemDefinitionList,
                        final String className, final ClassNameDefinition classNameDefinition,
                        final ClassCreatorDefinition classCreatorDefinition,
                        final DtoTemplateManager dtoTemplateManager,
                        final DtoTemplateReplacementSequenceManager dtoTemplateReplacementSequenceManager,
                        final Map<String, String> formattedResources) {
                logger.atInfo().log("START");

                Objects.requireNonNull(classItemDefinitionList, "ClassItemDefinitionList must not be null.");
                Objects.requireNonNull(className, "Class name must not be null.");
                Objects.requireNonNull(classNameDefinition, "ClassNameDefinition must not be null.");
                Objects.requireNonNull(classCreatorDefinition, "ClassCreatorDefinition must not be null.");
                Objects.requireNonNull(dtoTemplateManager, "DtoTemplateManager must not be null.");
                Objects.requireNonNull(dtoTemplateReplacementSequenceManager,
                                "DtoTemplateReplacementSequenceManager must not be null.");
                Objects.requireNonNull(formattedResources, "FormattedResources must not be null.");
                assert !classItemDefinitionList.isEmpty();
                assert !className.isEmpty();

                boolean isAllVariablesInvariant = true;
                boolean isAllVariablesVariant = true;
                for (ClassItemDefinition classItemDefinition : classItemDefinitionList) {

                        this.createFieldsDefinition(classItemDefinition, dtoTemplateManager,
                                        dtoTemplateReplacementSequenceManager);

                        if (classItemDefinition.isInvariant()) {
                                if (isAllVariablesVariant) {
                                        logger.atInfo().log("不変フィールドが含まれています。");
                                        isAllVariablesVariant = false;
                                }

                                this.createRequiredConstructor(classItemDefinition, dtoTemplateManager,
                                                dtoTemplateReplacementSequenceManager);
                        } else {
                                if (isAllVariablesInvariant) {
                                        logger.atInfo().log("可変フィールドが含まれています。");
                                        isAllVariablesInvariant = false;
                                }
                        }

                        this.createCopyingConstructorProcess(className, classItemDefinition, dtoTemplateManager,
                                        dtoTemplateReplacementSequenceManager);

                        final List<ClassDefinition> childClassDefinitionList = classItemDefinition
                                        .getChildClassDefinitionList();

                        if (!childClassDefinitionList.isEmpty()) {
                                logger.atInfo().log("子クラスが存在するため再帰処理を開始します。");

                                final RecursiveRequiredParameters newRequiredParameters = RecursiveRequiredParameters
                                                .of(dtoTemplateManager, dtoTemplateReplacementSequenceManager,
                                                                classNameDefinition, classCreatorDefinition,
                                                                childClassDefinitionList, formattedResources);

                                if (!this.formatClassDefinitionRecursively(newRequiredParameters)) {
                                        logger.atSevere().log("子クラス定義情報を生成するための再起処理が異常終了しました。");
                                        return null;
                                }
                        }
                }

                final DtoClassTemplate dtoClassTemplate = isAllVariablesInvariant
                                ? DtoClassTemplate.CLASS_BOILERPLATE_ONLY_REQUIRED_CONSTRUCTOR
                                : isAllVariablesVariant ? DtoClassTemplate.CLASS_BOILERPLATE_ONLY_DEFAULT_CONSTRUCTOR
                                                : DtoClassTemplate.CLASS_BOILERPLATE;

                logger.atInfo().log("DTOクラス雛形区分 = (%s)", dtoClassTemplate);
                logger.atInfo().log("END");
                return dtoClassTemplate;
        }

        /**
         * 引数として渡された情報を基に雛形の置換文字列を値に置き換えます。<br>
         * <br>
         * 第2引数として渡すマップには以下の値を紐づけて格納してください。<br>
         * 1, Key 置換対象の文字列<br>
         * 2, Value 置換後の値<br>
         *
         * @param template     雛形
         * @param replacements 置換対象文字列と値を格納したマップ
         * @return 引数の情報に基づいて置換された新しい文字列
         * @see DtoTemplate
         * @see DtoTemplateReplacementSequence
         * @see DtoTemplateManager
         * @see DtoClassTemplateManager
         * @see DtoTemplateReplacementSequenceManager
         */
        private String replaceTemplate(final String template, final Map<String, String> replacements) {
                logger.atInfo().log("SATRT");

                Objects.requireNonNull(template, "Template must not be null");
                Objects.requireNonNull(replacements, "Replacement map must not be null");
                assert !template.isEmpty();

                String formattedTemplate = template;
                final Set<Entry<String, String>> replacementRecords = replacements.entrySet();

                for (Entry<String, String> replacementRecord : replacementRecords) {
                        formattedTemplate = StringUtils.replace(formattedTemplate, replacementRecord.getKey(),
                                        replacementRecord.getValue());
                }

                logger.atInfo().log("置換された雛形 = (%s)", formattedTemplate);
                logger.atInfo().log("END");
                return formattedTemplate;
        }

        /**
         * 各データクラスの情報を基に引数有りのコンストラクタを生成します。<br>
         * 当メソッドで実行される生成メソッドは下記の通りです。<br>
         * <br>
         * {@link #createConstructorParamAnnotations(ClassItemDefinition, DtoTemplateManager, DtoTemplateReplacementSequenceManager)}
         * {@link #createConstructorParameters(ClassItemDefinition, DtoTemplateManager, DtoTemplateReplacementSequenceManager)}
         * {@link #createConstructorProcess(ClassItemDefinition, DtoTemplateManager, DtoTemplateReplacementSequenceManager)}
         *
         * @param classItemDefinition                   クラス項目情報
         * @param dtoTemplateManager                    DTOの雛形情報を管理するルールクラス
         * @param dtoTemplateReplacementSequenceManager DTOの雛形置換文字列を管理するルールクラス
         * @see #createConstructorParamAnnotations(ClassItemDefinition,
         *      DtoTemplateManager, DtoTemplateReplacementSequenceManager)
         * @see #createConstructorParameters(ClassItemDefinition, DtoTemplateManager,
         *      DtoTemplateReplacementSequenceManager)
         * @see #createConstructorProcess(ClassItemDefinition, DtoTemplateManager,
         *      DtoTemplateReplacementSequenceManager)
         */
        private void createRequiredConstructor(final ClassItemDefinition classItemDefinition,
                        final DtoTemplateManager dtoTemplateManager,
                        final DtoTemplateReplacementSequenceManager dtoTemplateReplacementSequenceManager) {
                logger.atInfo().log("START");

                Objects.requireNonNull(classItemDefinition, "ClassItemDefinition must not be null.");
                Objects.requireNonNull(dtoTemplateManager, "DtoTemplateManager must not be null.");
                Objects.requireNonNull(dtoTemplateReplacementSequenceManager,
                                "DtoTemplateReplacementSequenceManager must not be null.");

                this.createConstructorParamAnnotations(classItemDefinition, dtoTemplateManager,
                                dtoTemplateReplacementSequenceManager);

                this.createConstructorParameters(classItemDefinition, dtoTemplateManager,
                                dtoTemplateReplacementSequenceManager);

                this.createConstructorProcess(classItemDefinition, dtoTemplateManager,
                                dtoTemplateReplacementSequenceManager);

                logger.atInfo().log("END");
        }

        /**
         * 引数として渡された情報に基づいてフィールド定義を生成します。
         *
         * @param classItemDefinition                   クラス項目情報
         * @param dtoTemplateManager                    DTOの雛形情報を管理するルールクラス
         * @param dtoTemplateReplacementSequenceManager DTOの雛形置換文字列を管理するルールクラス
         */
        private void createFieldsDefinition(final ClassItemDefinition classItemDefinition,
                        final DtoTemplateManager dtoTemplateManager,
                        final DtoTemplateReplacementSequenceManager dtoTemplateReplacementSequenceManager) {
                logger.atInfo().log("START");

                Objects.requireNonNull(classItemDefinition, "ClassItemDefinition must not be null.");
                Objects.requireNonNull(dtoTemplateManager, "ClassTemplateManager must not be null.");
                Objects.requireNonNull(dtoTemplateReplacementSequenceManager,
                                "DtoTemplateReplacementSequenceManager must not be null.");

                final String replacementFieldDescription = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.VARIABLE_DESCRIPTION);
                final String replacementDataType = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.DATA_TYPE);
                final String replacementVariableName = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.VARIABLE_NAME);
                final String replacementInitialValue = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.INITIAL_VALUE);

                final String templateFieldJavadoc = dtoTemplateManager.getTemplateByKey(DtoTemplate.FIELD_JAVADOC);
                final String templateFieldDefinition = dtoTemplateManager
                                .getTemplateByKey(DtoTemplate.FIELD_DEFINITION);

                final Map<String, String> replacementsFieldJavadoc = new HashMap<>(1);
                replacementsFieldJavadoc.put(replacementFieldDescription, classItemDefinition.getDescription());

                final Map<String, String> replacementsFieldDefinition = new HashMap<>(3);
                replacementsFieldDefinition.put(replacementDataType, classItemDefinition.getDataType());
                replacementsFieldDefinition.put(replacementVariableName, classItemDefinition.getVariableName());
                replacementsFieldDefinition.put(replacementInitialValue, classItemDefinition.getInitialValue());

                final String fieldJavadoc = this.replaceTemplate(templateFieldJavadoc, replacementsFieldJavadoc);
                final String fieldDefinition = this.replaceTemplate(templateFieldDefinition,
                                replacementsFieldDefinition);

                this.prepareFieldsDefinition(fieldJavadoc, fieldDefinition);

                logger.atInfo().log("END");
        }

        /**
         * 引数として渡された情報に基づいてコンストラクタのParamアノテーション情報を生成します。<br>
         *
         * @param classItemDefinition                   クラス項目情報
         * @param dtoTemplateManager                    DTOの雛形情報を管理するルールクラス
         * @param dtoTemplateReplacementSequenceManager DTOの雛形置換文字列を管理するルールクラス
         */
        private void createConstructorParamAnnotations(final ClassItemDefinition classItemDefinition,
                        final DtoTemplateManager dtoTemplateManager,
                        final DtoTemplateReplacementSequenceManager dtoTemplateReplacementSequenceManager) {
                logger.atInfo().log("START");

                Objects.requireNonNull(classItemDefinition, "ClassItemDefinition must not be null.");
                Objects.requireNonNull(dtoTemplateManager, "ClassTemplateManager must not be null.");
                Objects.requireNonNull(dtoTemplateReplacementSequenceManager,
                                "DtoTemplateReplacementSequenceManager must not be null.");

                final String replacementVariableName = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.VARIABLE_NAME);
                final String replacementVariableDescription = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.VARIABLE_DESCRIPTION);

                final String templateConstructorParamAnnotation = dtoTemplateManager
                                .getTemplateByKey(DtoTemplate.CONSTRUCTOR_PARAM_ANNOTATION);

                final Map<String, String> replacementsConstructorParamAnnotation = new HashMap<>(2);
                replacementsConstructorParamAnnotation.put(replacementVariableName,
                                classItemDefinition.getVariableName());
                replacementsConstructorParamAnnotation.put(replacementVariableDescription,
                                classItemDefinition.getDescription());

                final String costructorParamAnnotation = this.replaceTemplate(templateConstructorParamAnnotation,
                                replacementsConstructorParamAnnotation);

                this.prepareConstructorParamAnnotations(costructorParamAnnotation);

                logger.atInfo().log("END");
        }

        /**
         * 引数として渡された情報に基づいてコンストラクタのパラメータ情報を生成します。<br>
         *
         * @param classItemDefinition                   クラス項目情報
         * @param dtoTemplateManager                    DTOの雛形情報を管理するルールクラス
         * @param dtoTemplateReplacementSequenceManager DTOの雛形置換文字列を管理するルールクラス
         */
        private void createConstructorParameters(final ClassItemDefinition classItemDefinition,
                        final DtoTemplateManager dtoTemplateManager,
                        final DtoTemplateReplacementSequenceManager dtoTemplateReplacementSequenceManager) {
                logger.atInfo().log("START");

                Objects.requireNonNull(classItemDefinition, "ClassItemDefinition must not be null.");
                Objects.requireNonNull(dtoTemplateManager, "ClassTemplateManager must not be null.");
                Objects.requireNonNull(dtoTemplateReplacementSequenceManager,
                                "DtoTemplateReplacementSequenceManager must not be null.");

                final String replacementDataType = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.DATA_TYPE);
                final String replacementVariableName = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.VARIABLE_NAME);

                final String templateFinalParameter = dtoTemplateManager.getTemplateByKey(DtoTemplate.FINAL_PARAMETER);

                final Map<String, String> replacementsConstructorParameters = new HashMap<>(2);
                replacementsConstructorParameters.put(replacementDataType, classItemDefinition.getDataType());
                replacementsConstructorParameters.put(replacementVariableName, classItemDefinition.getVariableName());

                final String constructorParameter = this.replaceTemplate(templateFinalParameter,
                                replacementsConstructorParameters);

                this.prepareConstructorParameters(constructorParameter);

                logger.atInfo().log("END");
        }

        /**
         * 引数として渡された情報に基づいてコンストラクタ処理を生成します。<br>
         *
         * @param classItemDefinition                   クラス項目情報
         * @param dtoTemplateManager                    DTOの雛形情報を管理するルールクラス
         * @param dtoTemplateReplacementSequenceManager DTOの雛形置換文字列を管理するルールクラス
         */
        private void createConstructorProcess(final ClassItemDefinition classItemDefinition,
                        final DtoTemplateManager dtoTemplateManager,
                        final DtoTemplateReplacementSequenceManager dtoTemplateReplacementSequenceManager) {
                logger.atInfo().log("START");

                Objects.requireNonNull(classItemDefinition, "ClassItemDefinition must not be null.");
                Objects.requireNonNull(dtoTemplateManager, "ClassTemplateManager must not be null.");
                Objects.requireNonNull(dtoTemplateReplacementSequenceManager,
                                "DtoTemplateReplacementSequenceManager must not be null.");

                final String replacementVariableName = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.VARIABLE_NAME);
                final String templateConstructorProcess = dtoTemplateManager
                                .getTemplateByKey(DtoTemplate.CONSTRUCTOR_PROCESS);

                final Map<String, String> replacementsConstructorProcess = new HashMap<>(1);
                replacementsConstructorProcess.put(replacementVariableName, classItemDefinition.getVariableName());

                final String constructorProcess = this.replaceTemplate(templateConstructorProcess,
                                replacementsConstructorProcess);

                this.prepareConstructorProcess(constructorProcess);

                logger.atInfo().log("END");
        }

        /**
         * 引数として渡された情報に基づいてコピーコンストラクタのパラメータ情報を生成します。<br>
         *
         * @param className                             クラス名
         * @param dtoTemplateManager                    DTOの雛形情報を管理するルールクラス
         * @param dtoTemplateReplacementSequenceManager DTOの雛形置換文字列を管理するルールクラス
         * @return コピーコンストラクタの引数情報
         */
        private String getCopyingConstructorParameter(final String className,
                        final DtoTemplateManager dtoTemplateManager,
                        final DtoTemplateReplacementSequenceManager dtoTemplateReplacementSequenceManager) {
                logger.atInfo().log("START");

                Objects.requireNonNull(className, "Class name must not be null.");
                Objects.requireNonNull(dtoTemplateManager, "ClassTemplateManager must not be null.");
                Objects.requireNonNull(dtoTemplateReplacementSequenceManager,
                                "DtoTemplateReplacementSequenceManager must not be null.");
                assert !className.isEmpty();

                final String replacementDataType = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.DATA_TYPE);
                final String replacementVariableName = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.VARIABLE_NAME);

                final String templateFinalParameter = dtoTemplateManager.getTemplateByKey(DtoTemplate.FINAL_PARAMETER);

                final Map<String, String> replacementsConstructorParameters = new HashMap<>(2);
                replacementsConstructorParameters.put(replacementDataType, className);
                replacementsConstructorParameters.put(replacementVariableName, this.toInitialLowerCase(className));

                logger.atInfo().log("END");
                return this.replaceTemplate(templateFinalParameter, replacementsConstructorParameters);
        }

        /**
         * 引数として渡された情報に基づいてコピーコンストラクタ処理を生成します。<br>
         *
         * @param className                             クラス名
         * @param classItemDefinition                   クラス項目情報
         * @param dtoTemplateManager                    DTOの雛形情報を管理するルールクラス
         * @param dtoTemplateReplacementSequenceManager DTOの雛形置換文字列を管理するルールクラス
         */
        private void createCopyingConstructorProcess(final String className,
                        final ClassItemDefinition classItemDefinition, final DtoTemplateManager dtoTemplateManager,
                        final DtoTemplateReplacementSequenceManager dtoTemplateReplacementSequenceManager) {
                logger.atInfo().log("SATRT");

                Objects.requireNonNull(className, "Class name must not be null.");
                Objects.requireNonNull(classItemDefinition, "ClassItemDefinition must not be null.");
                Objects.requireNonNull(dtoTemplateManager, "ClassTemplateManager must not be null.");
                Objects.requireNonNull(dtoTemplateReplacementSequenceManager,
                                "DtoTemplateReplacementSequenceManager must not be null.");
                assert !className.isEmpty();

                final String replacementVariableName = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.VARIABLE_NAME);
                final String replacementCopyingConstructorParameterValue = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.COPYING_CONSTRUCTOR_PARAMETER_VALUE);

                final String templateCopyingConstructorProcess = dtoTemplateManager
                                .getTemplateByKey(DtoTemplate.COPYING_CONSTRUCTOR_PROCESS);

                final Map<String, String> replacementsCopyingConstructorProcess = new HashMap<>(2);
                replacementsCopyingConstructorProcess.put(replacementVariableName,
                                this.toInitialUpperCase(classItemDefinition.getVariableName()));
                replacementsCopyingConstructorProcess.put(replacementCopyingConstructorParameterValue,
                                this.toInitialLowerCase(className));

                final String copyingConstructorProcess = this.replaceTemplate(templateCopyingConstructorProcess,
                                replacementsCopyingConstructorProcess);

                this.prepareCopyingConstructorProcess(copyingConstructorProcess);

                logger.atInfo().log("END");
        }

        private String createResource(final ResourceRequiredElements resourceRequiredElements) {
                logger.atInfo().log("START");
                logger.atInfo().log("リソース生成時の必須要素 = (%s)", resourceRequiredElements);

                Objects.requireNonNull(resourceRequiredElements, "ResourceRequiredElements must not be null.");

                final ClassNameDefinition classNameDefinition = resourceRequiredElements.getClassNameDefinition();
                final ClassCreatorDefinition classCreatorDefinition = resourceRequiredElements
                                .getClassCreatorDefinition();
                final ClassDefinition classDefinition = resourceRequiredElements.getClassDefinition();
                final DtoTemplateManager dtoTemplateManager = resourceRequiredElements.getDtoTemplateManager();
                final DtoTemplateReplacementSequenceManager dtoTemplateReplacementSequenceManager = resourceRequiredElements
                                .getDtoTemplateReplacementSequenceManager();
                final StringBuilder fieldsDefinition = resourceRequiredElements.getFieldsDefinition();
                final StringBuilder constructorParamAnnotations = resourceRequiredElements
                                .getConstructorParamAnnotations();
                final StringBuilder constructorParameters = resourceRequiredElements.getConstructorParameters();
                final StringBuilder constructorProcess = resourceRequiredElements.getConstructorProcess();
                final String copyingConstructorParameter = resourceRequiredElements.getCopyingConstructorParameter();
                final StringBuilder copyingConstructorProcess = resourceRequiredElements.getCopyingConstructorProcess();

                final String replacementProjectName = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.PROJECT_NAME);
                final String replacementClassName = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.CLASS_NAME);
                final String replacementCreationDate = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.CREATION_DATE);
                final String replacementUpdateDate = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.UPDATE_DATE);
                final String replacementCopyrightYear = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.COPYRIGHT_YEAR);
                final String replacementCreator = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.CREATOR);
                final String replacementPackageName = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.PACKAGE_NAME);
                final String replacementClassDescription = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.CLASS_DESCRIPTION);
                final String replacementFields = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.FIELDS);
                final String replacementConstructorParameters = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.CONSTRUCTOR_PARAMETERS);
                final String replacementConstructorProcess = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.REQUIRED_CONSTRUCTOR_PROCESS);
                final String replacementCopyingConstructorParameter = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.COPYING_CONSTRUCTOR_PARAMETER);
                final String replacementCopyingConstructorProcess = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.COPYING_CONSTRUCTOR_PROCESS);
                final String replacementParamAnnotationRequiredConstructor = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.PARAM_ANOTATION_REQUIRED_CONSTRUCTOR);
                final String replacementParamAnnotationCopyingConstructor = dtoTemplateReplacementSequenceManager
                                .getTemplateByKey(DtoTemplateReplacementSequence.PARAM_ANOTATION_COPYING_CONSTRUCTOR);

                final int newLineLength = EscapeSequence.NEW_LINE.getEscapeSequence().length() + 1;
                final int delimiterLength = Delimiter.COMMA.getDelimiter().length()
                                + EscapeSequence.SPACE.getEscapeSequence().length();

                final String creationDate = classCreatorDefinition.getCreationDate();
                fieldsDefinition.setLength(fieldsDefinition.length() - (newLineLength * 2));
                constructorParameters.setLength(constructorParameters.length() - delimiterLength);

                final Map<String, String> replacementsResource = new HashMap<>();
                replacementsResource.put(replacementProjectName, classNameDefinition.getProjectName());
                replacementsResource.put(replacementClassName, classDefinition.getClassName());
                replacementsResource.put(replacementCreationDate, creationDate);
                replacementsResource.put(replacementUpdateDate, classCreatorDefinition.getUpdateDate());
                replacementsResource.put(replacementCopyrightYear, creationDate.substring(0, 4));
                replacementsResource.put(replacementCreator, classCreatorDefinition.getCreator());
                replacementsResource.put(replacementPackageName, classNameDefinition.getPackageName());
                replacementsResource.put(replacementClassDescription, classDefinition.getDescription());
                replacementsResource.put(replacementFields, fieldsDefinition.toString());
                replacementsResource.put(replacementConstructorParameters, constructorParameters.toString());

                logger.atInfo().log("END");
                return this.replaceTemplate(resourceRequiredElements.getTemplate(), replacementsResource);
        }

        /**
         * 文字列の頭文字を大文字に変換して返却します。
         *
         * @param sequence 変換対象文字列
         * @return 頭文字列を大文字に変換された文字列
         */
        private String toInitialUpperCase(final String sequence) {
                Objects.requireNonNull(sequence, "String must not be null.");
                assert !sequence.isEmpty();
                return sequence.substring(0, 1).toUpperCase() + sequence.substring(1);
        }

        /**
         * 文字列の頭文字を小文字に変換して返却します。
         *
         * @param sequence 変換対象文字列
         * @return 頭文字列を小文字に変換された文字列
         */
        private String toInitialLowerCase(final String sequence) {
                Objects.requireNonNull(sequence, "String must not be null.");
                assert !sequence.isEmpty();
                return sequence.substring(0, 1).toLowerCase() + sequence.substring(1);
        }

        /**
         * 引数として渡された情報をJavaリソースとして出力する形式でフィールド定義情報へ追加します。
         *
         * @param fieldJavadoc    フィールドJavadoc定義
         * @param fieldDefinition フィールド定義
         */
        private void prepareFieldsDefinition(final String fieldJavadoc, final String fieldDefinition) {
                Objects.requireNonNull(fieldJavadoc, "Field Javadoc must not be null");
                Objects.requireNonNull(fieldDefinition, "Field definition must not be null.");
                assert !fieldJavadoc.isEmpty();
                assert !fieldDefinition.isEmpty();

                this.getFieldsDefinition().append(fieldJavadoc).append(EscapeSequence.NEW_LINE.getEscapeSequence())
                                .append(fieldDefinition).append(EscapeSequence.NEW_LINE.getEscapeSequence())
                                .append(EscapeSequence.NEW_LINE.getEscapeSequence());
        }

        /**
         * 引数として渡された情報をJavaリソースとして出力する形式でコンストラクタParamアノテーション情報へ追加します。
         *
         * @param costructorParamAnnotation コンストラクタのParamアノテーション定義
         */
        private void prepareConstructorParamAnnotations(final String costructorParamAnnotation) {
                Objects.requireNonNull(costructorParamAnnotation, "Costructor param annotation must not be null");
                assert !costructorParamAnnotation.isEmpty();

                this.getConstructorParamAnnotations().append(costructorParamAnnotation)
                                .append(EscapeSequence.NEW_LINE.getEscapeSequence());
        }

        /**
         * 引数として渡された情報をJavaリソースとして出力する形式でコンストラクタ引数情報へ追加します。
         *
         * @param constructorParameter コンストラクタ引数定義
         */
        private void prepareConstructorParameters(final String constructorParameter) {
                Objects.requireNonNull(constructorParameter, "Constructor parameter must not be null");
                assert !constructorParameter.isEmpty();

                this.getConstructorParameters().append(constructorParameter).append(Delimiter.COMMA.getDelimiter())
                                .append(EscapeSequence.SPACE.getEscapeSequence());
        }

        /**
         * 引数として指定された情報をJavaリソースへ出力する形式でコンストラクタ処理情報へ追加します。
         *
         * @param constructorProcess コンストラクタ処理定義
         */
        private void prepareConstructorProcess(final String constructorProcess) {
                Objects.requireNonNull(constructorProcess, "Constructor process must not be null");
                assert !constructorProcess.isEmpty();

                this.getConstructorProcess().append(constructorProcess)
                                .append(EscapeSequence.NEW_LINE.getEscapeSequence());
        }

        /**
         * 引数として指定された情報をJavaリソースへ出力する形式でコピーコンストラクタ処理情報へ追加します。
         *
         * @param copyingConstructorProcess コピーコンストラクタ処理定義
         */
        private void prepareCopyingConstructorProcess(final String copyingConstructorProcess) {
                Objects.requireNonNull(copyingConstructorProcess, "Copying constructor process must not be null");
                assert !copyingConstructorProcess.isEmpty();

                this.getCopyingConstructorProcess().append(copyingConstructorProcess)
                                .append(EscapeSequence.NEW_LINE.getEscapeSequence());
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
                 * DTOの雛形情報を管理するルールクラス
                 */
                @NonNull
                private final DtoTemplateManager dtoTemplateManager;

                /**
                 * DTOの雛形置換文字列を管理するルールクラス
                 */
                @NonNull
                private final DtoTemplateReplacementSequenceManager dtoTemplateReplacementSequences;

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

        /**
         * リソース生成時に必要となる要素情報を管理するデータクラスです。<br>
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
        private static class ResourceRequiredElements implements Serializable {

                /**
                 * シリアルバージョンUID
                 */
                private static final long serialVersionUID = -1895951084408414585L;

                /**
                 * リソースの雛形
                 */
                @NonNull
                final String template;

                /**
                 * クラス名定義情報
                 */
                @NonNull
                final ClassNameDefinition classNameDefinition;

                /**
                 * クラス作成者情報
                 */
                @NonNull
                final ClassCreatorDefinition classCreatorDefinition;

                /**
                 * クラス定義情報
                 */
                @NonNull
                final ClassDefinition classDefinition;

                /**
                 * DTOの雛形情報を管理するルールクラス
                 */
                @NonNull
                final DtoTemplateManager dtoTemplateManager;

                /**
                 * DTOの雛形置換文字列情報を管理するルールクラス
                 */
                @NonNull
                final DtoTemplateReplacementSequenceManager dtoTemplateReplacementSequenceManager;

                /**
                 * フィールド定義情報
                 */
                @NonNull
                final StringBuilder fieldsDefinition;

                /**
                 * コンストラクタのParamアノテーション情報
                 */
                @NonNull
                final StringBuilder constructorParamAnnotations;

                /**
                 * コンストラクタの引数情報
                 */
                @NonNull
                final StringBuilder constructorParameters;

                /**
                 * コンストラクタの処理情報
                 */
                @NonNull
                final StringBuilder constructorProcess;

                /**
                 * コピーコンストラクタの引数
                 */
                @NonNull
                final String copyingConstructorParameter;

                /**
                 * コピーコンストラクタの処理情報
                 */
                @NonNull
                final StringBuilder copyingConstructorProcess;
        }
}
