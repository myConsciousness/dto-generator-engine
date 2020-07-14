/**
 * Project Name : Generator<br>
 * File Name : ClassDefinitionManager.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/24<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.dtogenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.google.common.flogger.FluentLogger;

import org.apache.commons.lang3.StringUtils;
import org.thinkit.common.rule.AbstractRule;
import org.thinkit.common.rule.Attribute;
import org.thinkit.common.rule.Content;
import org.thinkit.common.util.FluentSheet;
import org.thinkit.common.util.FluentWorkbook;
import org.thinkit.common.util.Matrix;
import org.thinkit.generator.catalog.dtogenerator.DtoCellItem;
import org.thinkit.generator.dtogenerator.ClassDefinition;
import org.thinkit.generator.dtogenerator.ClassItemDefinition;
import org.thinkit.generator.rule.Sheet;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Excelに記述された定義書シートからクラス定義情報を抽出する処理を行うルールです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class ClassDefinitionManager extends AbstractRule {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * 再帰処理開始時のインデックス
     */
    private static final int RECURSIVE_START_INDEX = 0;

    /**
     * 再帰処理開始時の基準層
     */
    private static final int RECURSIVE_BASE_LAYER = 1;

    /**
     * Sheetオブジェクト
     */
    private FluentSheet sheet = null;

    /**
     * ファイルパス
     */
    private String filePath = "";

    /**
     * クラス定義情報群
     */
    @Getter
    private List<ClassDefinition> classDefinitionList = null;

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private ClassDefinitionManager() {
    }

    /**
     * コンストラクタ
     *
     * @param filePath DTO定義書のファイルパス
     * @exception IllegalArgumentException ファイルパスがnullまたは空文字列の場合
     */
    public ClassDefinitionManager(String filePath) {

        if (StringUtils.isEmpty(filePath)) {
            throw new IllegalArgumentException("wrong parameter was given. File path is required.");
        }

        this.filePath = filePath;

        super.loadContent(ContentName.クラス項目定義情報);
    }

    /**
     * コンストラクタ
     *
     * @param sheet DTO定義書の情報を持つSheetオブジェクト
     */
    public ClassDefinitionManager(@NonNull FluentSheet sheet) {
        this.sheet = sheet;
        super.loadContent(ContentName.クラス項目定義情報);
    }

    /**
     * シート名定数
     */
    private enum SheetName implements Sheet {
        定義書;

        @Override
        public String getString() {
            return this.name();
        }
    }

    /**
     * コンテンツ名定数
     */
    private enum ContentName implements Content {
        クラス項目定義情報;

        @Override
        public String getString() {
            return this.name();
        }
    }

    /**
     * コンテンツ要素定数
     */
    private enum ContentAttribute implements Attribute {
        セル項目コード, セル項目名;

        @Override
        public String getString() {
            return this.name();
        }
    }

    @Override
    public boolean execute() {
        logger.atInfo().log("START");

        if (this.sheet == null) {
            final FluentWorkbook workbook = new FluentWorkbook.Builder().fromFile(this.filePath).build();
            this.sheet = workbook.sheet(SheetName.定義書.name());
        }

        final List<ClassDefinition> classDefinitionList = this.getClassDefinitionList(sheet);

        if (classDefinitionList.isEmpty()) {
            logger.atSevere().log("クラス定義情報を取得できませんでした。");
            return false;
        }

        this.classDefinitionList = classDefinitionList;

        logger.atInfo().log("END");
        return true;
    }

    /**
     * Excelに定義されたマトリクステーブルからクラス定義情報群を取得し返却します。
     *
     * @param sheet Sheetオブジェクト
     * @return クラス定義情報群
     * @see #getClassDefinitionRecursively(List, List, List, int, int)
     */
    private List<ClassDefinition> getClassDefinitionList(FluentSheet sheet) {
        logger.atInfo().log("START");

        final List<Map<String, String>> contents = super.getContents();

        final String baseCellItem = this.getContentItem(contents, DtoCellItem.LOGICAL_DELETE);
        final Matrix baseIndexes = sheet.findCellIndex(baseCellItem);

        final List<Map<String, String>> matrixList = sheet.getMatrixList(baseIndexes.getColumn(), baseIndexes.getRow());
        logger.atInfo().log("マトリクスリスト = (%s)", matrixList);

        final List<ClassDefinition> classDefinitionList = new ArrayList<>();
        this.craeteClassDefinitionRecursively(RecursiveRequiredParameters.of(matrixList, contents, classDefinitionList,
                RECURSIVE_START_INDEX, RECURSIVE_BASE_LAYER));

        logger.atInfo().log("クラス定義情報群 = (%s)", classDefinitionList);
        logger.atInfo().log("END");
        return classDefinitionList;
    }

    /**
     * 引数として指定されたマトリクスリストから再帰的にクラス定義情報群を生成します。<br>
     * 再帰処理は各レコードが子クラスを持っている場合に実行されます。
     *
     * @param recursiveRequiredParameters 再帰処理時に必須となる情報を格納したデータクラス
     * @return 子クラスを生成する際に使用したレコード数
     * @see RecursiveRequiredParameters
     */
    private int craeteClassDefinitionRecursively(
            @NonNull final RecursiveRequiredParameters recursiveRequiredParameters) {
        logger.atInfo().log("START");

        final List<Map<String, String>> matrixList = recursiveRequiredParameters.getMatrixList();
        final List<Map<String, String>> contents = recursiveRequiredParameters.getContents();
        final List<ClassDefinition> classDefinitionList = recursiveRequiredParameters.getClassDefinitionList();
        final int startIndex = recursiveRequiredParameters.getStartIndex();
        final int baseItemLayer = recursiveRequiredParameters.getBaseItemLayer();

        logger.atInfo().log("開始インデックス = (%s)", startIndex);
        logger.atInfo().log("基準項目層 = (%s)", baseItemLayer);

        ClassDefinition parentClassDefinition = new ClassDefinition();
        List<ClassItemDefinition> classItemDefinitionList = new ArrayList<>();

        int recordCounter = 0;
        for (int i = startIndex, size = matrixList.size(); i < size; i++) {
            final Map<String, String> record = matrixList.get(i);

            final String itemNameLogicalDelete = this.getCellItemName(contents, DtoCellItem.LOGICAL_DELETE);
            final boolean logicalDelete = this.convertStringToBoolean(record.get(itemNameLogicalDelete));

            if (logicalDelete) {
                logger.atInfo().log("論理削除されたレコードのためスキップします。");
                logger.atInfo().log("スキップされたレコード = (%s)", record);
                recordCounter++;
                continue;
            }

            final String itemNameLayer = this.getCellItemName(contents, DtoCellItem.LAYER);
            final int layer = Integer.parseInt(record.get(itemNameLayer));
            logger.atInfo().log("レコードから取得した項目層 = (%s)", layer);

            if (layer + 1 < baseItemLayer) {
                logger.atInfo().log("%s層の処理を終了します。", baseItemLayer);
                logger.atInfo().log("戻り先の層 = (%s)", baseItemLayer - 2);
                break;
            }

            if (layer == baseItemLayer - 1 && layer % 2 == 0) {
                parentClassDefinition = new ClassDefinition();
                classItemDefinitionList = new ArrayList<>();

                parentClassDefinition.setClassItemDefinitionList(classItemDefinitionList);
                classDefinitionList.add(parentClassDefinition);

                this.createClassDefinition(contents, record, parentClassDefinition);
            } else {
                if (layer > baseItemLayer) {
                    logger.atInfo().log("子クラス情報を生成するため再帰処理を開始します。");

                    List<ClassDefinition> childClassDefinitionList = new ArrayList<>();
                    final int skipCounter = this.craeteClassDefinitionRecursively(RecursiveRequiredParameters
                            .of(matrixList, contents, childClassDefinitionList, i, baseItemLayer + 2));

                    classItemDefinitionList.get(classItemDefinitionList.size() - 1)
                            .setChildClassDefinitionList(childClassDefinitionList);

                    logger.atInfo().log("レコード番号 = (%s)", i);
                    logger.atInfo().log("スキップ数 = (%s)", skipCounter);
                    i += skipCounter - 1;
                } else {
                    this.createClassItemDefinition(contents, record, classItemDefinitionList);
                }
            }

            recordCounter++;
        }

        logger.atInfo().log("クラス定義情報（途中経過） = (%s)", classDefinitionList);
        return recordCounter;
    }

    /**
     * マトリクスから取得したレコードを基にクラス定義情報を生成します。
     *
     * @param content         コンテンツ
     * @param record          マトリクスレコード
     * @param classDefinition クラス定義情報
     */
    private void createClassDefinition(final List<Map<String, String>> content, final Map<String, String> record,
            final ClassDefinition classDefinition) {
        logger.atInfo().log("START");

        final String className = record.get(this.getCellItemName(content, DtoCellItem.VARIABLE_NAME));
        final String description = record.get(this.getCellItemName(content, DtoCellItem.DESCRIPTION));

        classDefinition.setClassName(className);
        classDefinition.setDescription(description);

        logger.atInfo().log("クラス定義情報 = (%s)", classDefinition.toString());
        logger.atInfo().log("END");
    }

    /**
     * マトリクスから取得した情報を基にクラス項目情報を生成します。
     *
     * @param content                 コンテンツ
     * @param record                  マトリクスレコード
     * @param classItemDefinitionList クラス項目情報リスト
     */
    private void createClassItemDefinition(List<Map<String, String>> content, Map<String, String> record,
            List<ClassItemDefinition> classItemDefinitionList) {
        logger.atInfo().log("START");

        final String variableName = record.get(this.getCellItemName(content, DtoCellItem.VARIABLE_NAME));
        final String dataType = record.get(this.getCellItemName(content, DtoCellItem.DATA_TYPE));
        final String initialValue = record.get(this.getCellItemName(content, DtoCellItem.INITIAL_VALUE));
        final boolean invariant = this
                .convertStringToBoolean(record.get(this.getCellItemName(content, DtoCellItem.INVARIANT)));
        final String description = record.get(this.getCellItemName(content, DtoCellItem.DESCRIPTION));

        final ClassItemDefinition classItemDefinition = new ClassItemDefinition(variableName, dataType, initialValue,
                invariant, description);

        classItemDefinitionList.add(classItemDefinition);

        logger.atInfo().log("クラス項目定義情報 = (%s)", classItemDefinition.toString());
        logger.atInfo().log("END");
    }

    /**
     * 文字列を真偽値に変換します。 <br>
     * 真偽値へ変換する際のルールは下記の通りです。<br>
     * 当該メソッドでは文字列に対するトリム加工は行いません。<br>
     * <br>
     * 1, 文字列がnullの場合: {@code false}<br>
     * 2, 文字列が空文字列の場合: {@code false}<br>
     * 3, 上記以外の場合: {@code true}<br>
     *
     * @param sequence 変換対象の文字列
     * @return 文字列がnullまたは空文字列の場合は{@code false}、それ以外は{@true}
     */
    private boolean convertStringToBoolean(final String sequence) {
        return !StringUtils.isEmpty(sequence);
    }

    /**
     * コンテンツリストから引数として指定されたセル項目オブジェクトのコード値と紐づくセル項目名を取得し返却します。
     *
     * @param content     コンテンツリスト
     * @param dtoCellItem 取得対象のセル項目コードが定義されたオブジェクト
     * @return 引数として指定されたセル項目コードに紐づくセル項目名
     * @exception IllegalArgumentException コンテンツリストが空の場合、またはセル項目オブジェクトがnullの場合
     */
    private String getCellItemName(List<Map<String, String>> content, DtoCellItem dtoCellItem) {
        logger.atInfo().log("START");

        Objects.requireNonNull(content, "Content must not be null.");
        Objects.requireNonNull(dtoCellItem, "DtoCellItem must not be null.");
        assert !content.isEmpty();

        final int cellItemCode = dtoCellItem.getCode();
        logger.atInfo().log("引数として渡されたセル項目コード = (%s)", cellItemCode);

        for (Map<String, String> elements : content) {
            final int code = Integer.parseInt(elements.get(ContentAttribute.セル項目コード.name()));
            logger.atInfo().log("コンテンツから取得したセル項目コード = (%s)", code);

            if (cellItemCode == code) {
                logger.atInfo().log("END");
                return elements.get(ContentAttribute.セル項目名.name());
            }
        }

        logger.atWarning().log("指定されたセル項目コードに紐づく要素がコンテンツに定義されていません。");
        logger.atInfo().log("END");
        return "";
    }

    /**
     * 指定されたセル項目に紐づくコンテンツ項目を取得し返却します。
     *
     * @param nodeList     コンテンツ項目リスト
     * @param cellItemName 取得対象のセル項目
     * @return 取得対象のセル項目に紐づくコンテンツ項目
     */
    private String getContentItem(List<Map<String, String>> nodeList, DtoCellItem cellItemName) {
        logger.atInfo().log("START");

        for (Map<String, String> node : nodeList) {
            final int cellItemCode = Integer.parseInt(node.get(ContentAttribute.セル項目コード.getString()));

            if (cellItemName.getCode() == cellItemCode) {
                final String contentItem = node.get(ContentAttribute.セル項目名.getString());
                logger.atInfo().log("取得したコンテンツ項目 = (%s)", contentItem);
                logger.atInfo().log("END");
                return contentItem;
            }
        }

        logger.atInfo().log("指定されたコンテンツ項目を取得できませんでした。");
        logger.atInfo().log("END");
        return "";
    }

    @Override
    protected List<Attribute> getAttributes() {
        logger.atInfo().log("START");

        final List<Attribute> attributes = new ArrayList<>(2);
        attributes.add(ContentAttribute.セル項目コード);
        attributes.add(ContentAttribute.セル項目名);

        logger.atInfo().log("クラス項目定義情報のアトリビュート = (%s)", attributes);
        logger.atInfo().log("END");
        return attributes;
    }

    /**
     * クラス定義情報を取得する際の再帰処理で必要となるパラメータ情報を管理するデータクラスです。
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
        private static final long serialVersionUID = 3989477460378492335L;

        /**
         * Excelの定義書シートから取得したマトリクスの生データリスト
         */
        @NonNull
        private final List<Map<String, String>> matrixList;

        /**
         * クラス項目のコンテンツ情報が格納されたマップ
         */
        @NonNull
        private final List<Map<String, String>> contents;

        /**
         * クラス定義情報リスト
         */
        @NonNull
        private final List<ClassDefinition> classDefinitionList;

        /**
         * 探索開始インデックス
         */
        private final int startIndex;

        /**
         * 基準項目層
         */
        private final int baseItemLayer;
    }
}