/**
 * Project Name : generator-commons<br>
 * File Name : ConditionNode.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/25<br>
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
 * コンテンツの条件ノードを管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class ConditionNode {

    /**
     * 条件ID
     */
    private String conditionId;

    /**
     * 除外
     */
    private boolean exclude;

    /**
     * デフォルトコンストラクタ
     */
    private ConditionNode() {
    }

    /**
     * コンストラクタ
     *
     * @param conditionId 条件ID
     * @param exclude     除外
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ConditionNode(@NonNull String conditionId, boolean exclude) {
        this.conditionId = conditionId;
        this.exclude = exclude;
    }

    /**
     * コピーコンストラクタ
     *
     * @param conditionNode コピーする条件ノードオブジェクト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ConditionNode(@NonNull ConditionNode conditionNode) {
        this.conditionId = conditionNode.getConditionId();
        this.exclude = conditionNode.isExclude();
    }

    /**
     * 引数として指定された情報をもとに {@link ConditionNode} クラスの新しいインスタンスを生成し返却します。
     *
     * @param conditionId 条件ID
     * @param exclude     除外
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ConditionNode of(@NonNull String conditionId, boolean exclude) {
        return new ConditionNode(conditionId, exclude);
    }

    /**
     * 引数として指定された {@code conditionNode} オブジェクトの情報をもとに {@link ConditionNode}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param conditionNode 条件ノードオブジェクト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ConditionNode of(@NonNull ConditionNode conditionNode) {
        return new ConditionNode(conditionNode);
    }
}