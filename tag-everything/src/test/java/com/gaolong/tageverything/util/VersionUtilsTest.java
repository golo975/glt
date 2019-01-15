package com.gaolong.tageverything.util;

import org.junit.Assert;
import org.junit.Test;

import static com.gaolong.tageverything.util.VersionUtils.*;

public class VersionUtilsTest {

    @Test
    public void convertPartnerVersionTest() {
        Assert.assertEquals(0, convertPartnerVersion(null).length);
        Assert.assertEquals(0, convertPartnerVersion("").length);
        Assert.assertEquals(0, convertPartnerVersion("  ").length);
        Assert.assertEquals(0, convertPartnerVersion("null").length);
        Assert.assertEquals(0, convertPartnerVersion(".").length);
        Assert.assertEquals(1, convertPartnerVersion("7").length);
        Assert.assertEquals(1, convertPartnerVersion("7..").length);
        Assert.assertEquals(1, convertPartnerVersion("7.abc.").length);
        Assert.assertEquals(2, convertPartnerVersion("7.2.").length);
        Assert.assertEquals(2, convertPartnerVersion("7.2.abc").length);
        Assert.assertEquals(3, convertPartnerVersion("7.2.1").length);
        Assert.assertEquals(3, convertPartnerVersion("7.2.1.1").length);
        Assert.assertEquals(3, convertPartnerVersion("7.2.1.abc").length);
        Assert.assertEquals(3, convertPartnerVersion("7.2.1..").length);
    }

    @Test
    public void compareTest() {
        Assert.assertEquals(VersionComparatorResult.unknown, compare(new Integer[]{}, new Integer[]{}));
        Assert.assertEquals(VersionComparatorResult.unknown, compare(new Integer[]{1}, new Integer[]{}));
        Assert.assertEquals(VersionComparatorResult.unknown, compare(new Integer[]{}, new Integer[]{1}));
        Assert.assertEquals(VersionComparatorResult.equals, compare(new Integer[]{1}, new Integer[]{1}));
        Assert.assertEquals(VersionComparatorResult.equals, compare(new Integer[]{1}, new Integer[]{1, 0}));
        Assert.assertEquals(VersionComparatorResult.less_than, compare(new Integer[]{1}, new Integer[]{1, 0, 1}));
        Assert.assertEquals(VersionComparatorResult.great_than, compare(new Integer[]{1, 2}, new Integer[]{1, 0, 1}));
        Assert.assertEquals(VersionComparatorResult.equals, compare(new Integer[]{1, 0, 1}, new Integer[]{1, 0, 1}));


        Assert.assertEquals(VersionComparatorResult.unknown, compare(null, new Integer[]{8, 2}));
        Assert.assertEquals(VersionComparatorResult.unknown, compare(new Integer[]{}, new Integer[]{8, 2}));
        Assert.assertEquals(VersionComparatorResult.less_than, compare(new Integer[]{8}, new Integer[]{8, 2}));
        Assert.assertEquals(VersionComparatorResult.less_than, compare(new Integer[]{7}, new Integer[]{8, 2}));
        Assert.assertEquals(VersionComparatorResult.less_than, compare(new Integer[]{8}, new Integer[]{8, 2}));
        Assert.assertEquals(VersionComparatorResult.less_than, compare(new Integer[]{8, 1}, new Integer[]{8, 2}));
        Assert.assertEquals(VersionComparatorResult.less_than, compare(new Integer[]{8, 1, 999}, new Integer[]{8, 2}));
    }
}
