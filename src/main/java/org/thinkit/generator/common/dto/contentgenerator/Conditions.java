package org.thinkit.generator.common.dto.contentgenerator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.thinkit.common.iterator.FluentIterator;
import org.thinkit.common.iterator.IterableNode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public final class Conditions implements Iterable<Condition>, IterableNode<Condition> {

    @Getter
    private List<Condition> conditions = List.of();

    private int size;

    /**
     * デフォルトコンストラクタ
     */
    private Conditions() {
        this.conditions = List.of();
        this.size = 0;
    }

    /**
     * コピーコンストラクタ
     *
     * @param conditions 条件群
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private Conditions(@NonNull Conditions conditions) {
        this.conditions = new ArrayList<>(conditions.getConditions());
        this.size = conditions.size();
    }

    /**
     * {@link Conditions} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link Conditions} クラスの新しいインスタンス
     */
    public static Conditions of() {
        return new Conditions();
    }

    /**
     * 引数として指定された {@code conditions} オブジェクトの情報をコピーした新しい {@link Conditions}
     * クラスのインスタンスを生成し返却します。
     */
    public static Conditions of(@NonNull Conditions conditions) {
        return new Conditions(conditions);
    }

    public Conditions add(@NonNull Condition condition) {
        conditions.add(condition);
        size++;

        return this;
    }

    @Override
    public List<Condition> nodes() {
        return this.conditions;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<Condition> iterator() {
        return FluentIterator.of(this);
    }
}