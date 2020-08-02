/**
 * Project Name : generator-commons<br>
 * File Name : ContentSelectionNode.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/25<br>
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
 * コンテンツの選択ノードを管理するデータクラスです。
 * <p>
 * このクラスはFluentインターフェースの概念を応用し設計されています。<br>
 * そのため、以下のようなメソッドチェーンでの操作が可能です。
 *
 * <pre>
 * 使用例:
 * <code>
 * ContentSelectionNode contentSelectionNode = ContentSelectionNode.of(conditionId)
 *                                            .put(key1, value1)
 *                                            .put(key2, value2);
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class ContentSelectionNode implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 3246450914037753704L;

    /**
     * 条件ID
     */
    @Getter
    private String conditionId;

    /**
     * コンテンツ選択グループ
     */
    @Getter
    private ContentSelectionGroup contentSelectionGroup;

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private ContentSelectionNode() {
    }

    /**
     * コンストラクタ
     *
     * @param conditionId           条件ID
     * @param contentSelectionGroup コンテンツ選択グループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentSelectionNode(@NonNull String conditionId, @NonNull ContentSelectionGroup contentSelectionGroup) {
        this.conditionId = conditionId;
        this.contentSelectionGroup = contentSelectionGroup;
    }

    /**
     * コピーコンストラクタ
     *
     * @param contentSelectionNode 選択ノード
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public ContentSelectionNode(@NonNull ContentSelectionNode contentSelectionNode) {
        this.conditionId = contentSelectionNode.getConditionId();
        this.contentSelectionGroup = ContentSelectionGroup.of(contentSelectionNode.getContentSelectionGroup());
    }

    /**
     * {@link ContentSelectionNode} クラスの新しいインスタンスを生成し返却します。
     *
     * @param conditionId           条件ID
     * @param contentSelectionGroup コンテンツ選択グループ
     * @return {@link ContentSelectionNode} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentSelectionNode of(@NonNull String conditionId,
            @NonNull ContentSelectionGroup contentSelectionGroup) {
        return new ContentSelectionNode(conditionId, contentSelectionGroup);
    }

    /**
     * 引数として渡された {@code contentSelectionNode}
     * オブジェクトの情報をコピーした{@link ContentSelectionNode} クラスの新しいインスタンスを生成し返却します。
     *
     * @param contentSelectionNode 選択ノード
     * @return {@link ContentSelectionNode} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentSelectionNode of(@NonNull ContentSelectionNode contentSelectionNode) {
        return new ContentSelectionNode(contentSelectionNode);
    }
}