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

package org.thinkit.generator.common.vo.content;

import java.io.Serializable;

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
@ToString
@EqualsAndHashCode
public final class ContentMatrix implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 7166357277027973034L;

    /**
     * 選択ノード群
     */
    @Getter
    private SelectionNodeGroup selectionNodeGroup;

    /**
     * 条件ノード群
     */
    @Getter
    private ConditionNodeGroup conditionNodeGroup;

    /**
     * デフォルトコンストラクタ
     */
    private ContentMatrix() {
    }

    /**
     * コンストラクタ
     *
     * @param selectionNodeGroup 選択ノード群
     * @param conditionNodeGroup 条件ノード群
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentMatrix(@NonNull SelectionNodeGroup selectionNodeGroup,
            @NonNull ConditionNodeGroup conditionNodeGroup) {
        this.selectionNodeGroup = selectionNodeGroup;
        this.conditionNodeGroup = conditionNodeGroup;
    }

    /**
     * コピーコンストラクタ
     *
     * @param contentMatrix コンテンツマトリクス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentMatrix(@NonNull ContentMatrix contentMatrix) {
        this.selectionNodeGroup = SelectionNodeGroup.of(contentMatrix.getSelectionNodeGroup());
        this.conditionNodeGroup = ConditionNodeGroup.of(contentMatrix.getConditionNodeGroup());
    }

    /**
     * 引数として指定された情報を基に {@link ContentMatrix} クラスの新しいインスタンスを生成し返却します。
     *
     * @param selectionNodeGroup 選択ノード群
     * @param conditionNodeGroup 条件ノード群
     * @return {@link ContentMatrix} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentMatrix of(@NonNull SelectionNodeGroup selectionNodeGroup,
            @NonNull ConditionNodeGroup conditionNodeGroup) {
        return new ContentMatrix(selectionNodeGroup, conditionNodeGroup);
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