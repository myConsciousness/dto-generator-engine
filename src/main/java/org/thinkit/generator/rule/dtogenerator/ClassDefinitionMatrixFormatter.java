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
import java.util.Objects;

import org.thinkit.common.rule.AbstractRule;
import org.thinkit.generator.dtogenerator.ClassCreatorDefinition;
import org.thinkit.generator.dtogenerator.ClassDefinition;
import org.thinkit.generator.dtogenerator.ClassDefinitionMatrix;
import org.thinkit.generator.dtogenerator.ClassItemDefinition;
import org.thinkit.generator.dtogenerator.ClassNameDefinition;

import com.google.common.flogger.FluentLogger;

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

        final ClassDefinitionMatrix classDefinitionMatrix = this.classDefinitionMatrix;
        final Map<String, String> formattedResources = new HashMap<>();

        final RecursiveRequiredParameters parameters = RecursiveRequiredParameters.of(
                classDefinitionMatrix.getClassNameDefinition(), classDefinitionMatrix.getClassCreatorDefinition(),
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

        final ClassNameDefinition classNameDefinition = parameters.getClassNameDefinition();
        final ClassCreatorDefinition classCreatorDefinition = parameters.getClassCreatorDefinition();
        final List<ClassDefinition> classDefinitionList = parameters.getClassDefinitionList();
        final Map<String, String> formattedResources = parameters.getFormattedResources();

        for (ClassDefinition classDefinition : classDefinitionList) {

            final List<ClassItemDefinition> classItemDefinitionList = classDefinition.getClassItemDefinitionList();
            final String className = classDefinition.getClassName();

            final boolean success = this.formatClassItemDefinition(classItemDefinitionList, classNameDefinition,
                    classCreatorDefinition, formattedResources);

            if (!success) {
                logger.atSevere().log("クラス項目定義情報の整形処理が異常終了しました。");
                return false;
            }

            String resource = "";
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
     * 再帰処理中に想定外のエラーが発生した場合は必ず{@code <code>null</code>}を返却します。
     *
     * @param classItemDefinitionList クラス項目定義情報リスト
     * @param classNameDefinition     クラス名定義情報
     * @param classCreatorDefinition  クラス作成者情報
     * @param formattedResources      整形されたJavaリソース情報
     * @return #see
     *         {@link #formatClassDefinitionRecursively(RecursiveRequiredParameters)}
     */
    private boolean formatClassItemDefinition(final List<ClassItemDefinition> classItemDefinitionList,
            final ClassNameDefinition classNameDefinition, final ClassCreatorDefinition classCreatorDefinition,
            final Map<String, String> formattedResources) {
        logger.atInfo().log("START");

        Objects.requireNonNull(classItemDefinitionList, "ClassItemDefinitionList must not be null.");
        Objects.requireNonNull(classNameDefinition, "ClassNameDefinition must not be null.");
        Objects.requireNonNull(classCreatorDefinition, "ClassCreatorDefinition must not be null.");
        Objects.requireNonNull(formattedResources, "FormattedResources must not be null.");
        assert !classItemDefinitionList.isEmpty();

        for (ClassItemDefinition classItemDefinition : classItemDefinitionList) {

            if (classItemDefinition.isInvariant()) {
                // 不変フィールド
            }

            final List<ClassDefinition> childClassDefinitionList = classItemDefinition.getChildClassDefinitionList();

            if (!childClassDefinitionList.isEmpty()) {
                logger.atInfo().log("子クラスが存在するため再帰処理を開始します。");

                final RecursiveRequiredParameters newRequiredParameters = RecursiveRequiredParameters
                        .of(classNameDefinition, classCreatorDefinition, childClassDefinitionList, formattedResources);

                if (!this.formatClassDefinitionRecursively(newRequiredParameters)) {
                    logger.atSevere().log("子クラス定義情報を生成するための再起処理が異常終了しました。");
                    return false;
                }
            }
        }

        logger.atInfo().log("END");
        return true;
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
