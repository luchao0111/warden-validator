package com.validator.warden.common

import com.validator.warden.WardenValidator
import com.validator.warden.core.domain.CheckResult
import com.validator.warden.core.domain.WdConstant
import org.junit.Assert
import spock.lang.Specification

/**
 * @author DandyLuo
 */
class EntityPropertiesTest extends Specification {

    /**
     * 测试指定的属性name
     * @return
     */
    def nameAsSpecifiedPropertyTest() {
        given:
        TestEntity entity = new TestEntity().setName(name).setAge(age)

        expect:
        CheckResult checkResult = WardenValidator.validate(WdConstant.DEFAULT_GROUP, entity, "name", "age");
        Boolean act = checkResult.isSuccess();
        Assert.assertEquals(result, act)
        if (!act) {
            println WardenValidator.errMsgChain;
        }

        where:
        name      | age | result
        "nihao"   | 12  | false
        "ok"      | 32  | false
        "ok"      | 2   | false
        "hehe"    | 20  | true
        "haohao"  | 30  | true
        null      | 30  | true
    }

    /**
     * 测试指定的属性age
     * @return
     */
    def ageAsSpecifiedPropertyTest() {
        given:
        TestEntity entity = new TestEntity().setName(name).setAge(age)

        expect:
        CheckResult checkResult = WardenValidator.validate(WdConstant.DEFAULT_GROUP, entity, "age");
        Boolean act = checkResult.isSuccess();
        Assert.assertEquals(result, act)
        if (!act) {
            println WardenValidator.errMsgChain
        }

        where:
        name     | age  | result
        "nihao"  | 12   | true
        "ok"     | 32   | true
        "hehe"   | 20   | true
        "haohao" | 40   | false
        null     | null | false
    }

    /**
     * 测试指定的属性address
     * @return
     */
    def addressAsSpecifiedPropertyTest() {
        given:
        TestEntity entity = new TestEntity().setName(name).setAge(age).setAddress(address)

        expect:
        CheckResult checkResult = WardenValidator.validate(WdConstant.DEFAULT_GROUP, entity, "address");
        def act = checkResult.isSuccess();
        Assert.assertEquals(result, act)
        if (!act) {
            println WardenValidator.errMsgChain
        }

        where:
        name     | age | address     | result
        "nihao"  | 12  | "beijing"   | true
        "ok"     | 32  | "shanghai"  | true
        "hehe"   | 20  | "hangzhou"  | false
        "haohao" | 40  | "zhengzhou" | false
    }
}
