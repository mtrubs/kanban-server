package com.mtrubs.kanban.resource;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author mrubino
 * @since 2014-07-19
 */
@Test(groups = {"UnitTest"})
public class ResourceSerializerUnitTest {

    @Test
    public void toJson() {
        TestData data = new TestData();
        data.a = 5;
        data.b = "test";

        ResourceSerializer serializer = new ResourceSerializer();
        String result = serializer.toJson(data);
        assertEquals(result, "{\"a\":5,\"b\":\"test\"}");
    }

    @Test
    public void fromJson() {
        ResourceSerializer serializer = new ResourceSerializer();
        TestData result = serializer.fromJson("{\"a\":5,\"b\":\"test\"}", TestData.class);
        assertEquals(result.a, 5);
        assertEquals(result.b, "test");
    }

    private class TestData {
        private int a;
        private String b;
    }
}
