package org.thinkit.generator.rule.factory.resource;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * プログラムリソースにおけるコンストラクタを抽象化した抽象クラスです。<br>
 * この抽象クラスではコンストラクタ定義に必要な情報を保持します。<br>
 * この抽象クラスを継承する具象クラスは必ず{@link Component#createResource()}を実装してください。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * @see Component
 * @see Component#createResource()
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public abstract class Constructor extends Function {

    /**
     * コンストラクタ
     * 
     * @param functionName        機能名
     * @param functionDescription 関数の説明
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    protected Constructor(String functionName, FunctionDescription functionDescription) {
        super(functionName, functionDescription);
    }
}