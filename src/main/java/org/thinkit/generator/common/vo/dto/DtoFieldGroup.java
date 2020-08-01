/**
 * Project Name : generator-commons<br>
 * File Name : DtoFieldGroup.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/26<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.vo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.thinkit.common.util.iterator.FluentIterator;
import org.thinkit.common.util.iterator.IterableNode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * DTOフィールドオブジェクトグループを管理するデータクラスです。
 * <p>
 * このクラスはFluentインターフェースの概念を応用し設計されています。<br>
 * そのため、以下のようなメソッドチェーンでの操作が可能です。
 *
 * <pre>
 * <code>
 * DtoFieldGroup dtoFieldGroup = DtoFieldGroup.of()
 *                                  .add(dtoField1)
 *                                  .add(dtoField2);
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class DtoFieldGroup implements Iterable<DtoField>, IterableNode<DtoField>, Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 4211335544432048358L;

    /**
     * DTOフィールドオブジェクトグループ
     */
    @Getter
    private List<DtoField> dtoFieldGroup;

    /**
     * DTOフィールドオブジェクトグループのサイズ
     */
    private int size;

    /**
     * デフォルトコンストラクタ
     */
    private DtoFieldGroup() {
        this.dtoFieldGroup = new ArrayList<>(0);
        this.size = 0;
    }

    /**
     * コピーコンストラクタ
     *
     * @param dtoFieldGroup DTOフィールドオブジェクトグループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoFieldGroup(@NonNull DtoFieldGroup dtoFieldGroup) {
        this.dtoFieldGroup = new ArrayList<>(dtoFieldGroup.getDtoFieldGroup());
        this.size = dtoFieldGroup.size();
    }

    /**
     * {@link DtoFieldGroup} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link DtoFieldGroup} クラスの新しいインスタンス
     */
    public static DtoFieldGroup of() {
        return new DtoFieldGroup();
    }

    /**
     * 引数として指定された {@code dtoFieldGroup} オブジェクトの情報をコピーした新しい {@link DtoFieldGroup}
     * クラスのインスタンスを生成し返却します。
     */
    public static DtoFieldGroup of(@NonNull DtoFieldGroup dtoFieldGroup) {
        return new DtoFieldGroup(dtoFieldGroup);
    }

    /**
     * 引数として渡された {@code dtoField} を条件リストへ追加します。
     * <p>
     * この {@link DtoFieldGroup#add(DtoField)}
     * メソッドは自分自身のインスタンスを返却するため以下のようなメソッドチェーンでの操作が可能です。
     *
     * <pre>
     * <code>
     * DtoFieldGroup dtoFieldGroup = DtoFieldGroup.of()
     *                                  .add(dtoField1)
     *                                  .add(dtoField2);
     * </code>
     * </pre>
     *
     * @param dtoField DTOフィールドオブジェクト
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public DtoFieldGroup add(@NonNull DtoField dtoField) {
        this.dtoFieldGroup.add(dtoField);
        size++;

        return this;
    }

    /**
     * {@link DtoField} クラスを総称型として持つストリームを返却します。
     *
     * @return {@link DtoField} クラスを総称型として持つストリーム
     */
    public Stream<DtoField> stream() {
        return this.dtoFieldGroup.stream();
    }

    @Override
    public List<DtoField> nodes() {
        return this.dtoFieldGroup;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<DtoField> iterator() {
        return FluentIterator.of(this);
    }
}