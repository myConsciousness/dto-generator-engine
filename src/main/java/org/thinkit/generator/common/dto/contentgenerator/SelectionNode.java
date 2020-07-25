/**
 * Project Name : generator-commons<br>
 * File Name : SelectionNode.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/25<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.dto.contentgenerator;

import java.util.HashMap;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンテンツにおける選択ノードの情報を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class SelectionNode {

    /**
     * 条件ID
     */
    private String conditionId = "";

    /**
     * 選択情報のセット
     */
    private Map<String, String> selection = new HashMap<>(0);

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private SelectionNode() {
    }

    /**
     * コンストラクタ
     *
     * @param conditionId 条件ID
     * @param selection   選択情報
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public SelectionNode(@NonNull String conditionId, @NonNull Map<String, String> selection) {
        this.conditionId = conditionId;
        this.selection = selection;
    }

    /**
     * コピーコンストラクタ
     *
     * @param selectionNode 選択ノード
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public SelectionNode(@NonNull SelectionNode selectionNode) {
        this.conditionId = selectionNode.getConditionId();
        this.selection = new HashMap<>(selectionNode.getSelection());
    }
}