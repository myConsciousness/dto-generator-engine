/**
 * Project Name : generator-commons<br>
 * File Name : ContentMatrix.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/26<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.dto.contentgenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンテンツのマトリクス情報を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class ContentMatrix {

    /**
     * 選択ノード群
     */
    private SelectionNodes selectionNodes;

    /**
     * 条件ノード群
     */
    private ConditionNodes conditionNodes;

    /**
     * デフォルトコンストラクタ
     */
    private ContentMatrix() {
    }

    /**
     * コンストラクタ
     *
     * @param selectionNodes 選択ノード群
     * @param conditionNodes 条件ノード群
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentMatrix(@NonNull SelectionNodes selectionNodes, @NonNull ConditionNodes conditionNodes) {
        this.selectionNodes = selectionNodes;
        this.conditionNodes = conditionNodes;
    }

    /**
     * コピーコンストラクタ
     *
     * @param contentMatrix コンテンツマトリクス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentMatrix(@NonNull ContentMatrix contentMatrix) {
        this.selectionNodes = SelectionNodes.of(contentMatrix.getSelectionNodes());
        this.conditionNodes = ConditionNodes.of(contentMatrix.getConditionNodes());
    }

    /**
     * 引数として指定された情報を基に {@link ContentMatrix} クラスの新しいインスタンスを生成し返却します。
     *
     * @param selectionNodes 選択ノード群
     * @param conditionNodes 条件ノード群
     * @return {@link ContentMatrix} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentMatrix of(@NonNull SelectionNodes selectionNodes, @NonNull ConditionNodes conditionNodes) {
        return new ContentMatrix(selectionNodes, conditionNodes);
    }

    /**
     * 引数として指定された {@code contentMatrix} の情報を基に {@link ContentMatrix}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param contentMatrix コンテンツマトリクス
     * @return {@link ContentMatrix} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentMatrix of(@NonNull ContentMatrix contentMatrix) {
        return new ContentMatrix(contentMatrix);
    }
}