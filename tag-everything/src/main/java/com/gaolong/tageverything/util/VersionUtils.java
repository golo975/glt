package com.gaolong.tageverything.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VersionUtils {

    private static final Logger logger = LoggerFactory.getLogger(VersionUtils.class);

    public static Integer[] convertPartnerVersion(String partnerVersion) {

        if (StringUtils.isBlank(partnerVersion)) {
            return new Integer[]{};
        }
        String[] split = partnerVersion.split("\\.");

        Integer first_version = null;
        Integer second_version = null;
        Integer third_version = null;

        try {
            int length = split.length;
            if (length > 0) {
                first_version = Integer.valueOf(split[0]);
            }
            if (length > 1) {
                second_version = Integer.valueOf(split[1]);
            }
            if (length > 2) {
                third_version = Integer.valueOf(split[2]);
            }
        } catch (NumberFormatException e) {
//            logger.error("convert partner_version error, partner_version = {}, NumberFormatException = {} ",
//                    partnerVersion, ExceptionUtil.outException(e));
        }

        if (first_version == null) {
            return new Integer[]{};
        }

        if (second_version == null) {
            return new Integer[]{first_version};
        }

        if (third_version == null) {
            return new Integer[]{first_version, second_version};
        }

        return new Integer[]{first_version, second_version, third_version};
    }

    public static VersionComparatorResult compare(Integer[] left, Integer[] right) {//todo 太复杂！！
        if (left == null || right == null) {
            return VersionComparatorResult.unknown;
        }
        VersionComparatorResult comparatorResultPrevious = VersionComparatorResult.unknown;
        VersionComparatorResult comparatorResultCurrent;
        for (int i = 0; true; i++) {
            comparatorResultCurrent = compare(left, right, i);
            if (comparatorResultCurrent.equals(VersionComparatorResult.unknown)) {
                comparatorResultCurrent = comparatorResultPrevious;
                break;
            }
            comparatorResultPrevious = comparatorResultCurrent;
            if (comparatorResultCurrent.equals(VersionComparatorResult.equals)) {
                continue;
            }
            break;
        }
        return comparatorResultCurrent;
    }

    private static VersionComparatorResult compare(Integer[] leftVersion, Integer[] rightVersion, int index) {

        if (index == 0 && (leftVersion.length <= index || rightVersion.length <= index)) {
            return VersionComparatorResult.unknown;
        }

        if (leftVersion.length <= index && rightVersion.length <= index) {
            return VersionComparatorResult.unknown;
        }

        Integer left = 0;
        if (leftVersion.length > index) {
            left = leftVersion[index];
        }
        Integer right = 0;
        if (rightVersion.length > index) {
            right = rightVersion[index];
        }

        return compare(left, right);
    }

    private static VersionComparatorResult compare(Integer left, Integer right) {
        if (left > right) {
            return VersionComparatorResult.great_than;
        }
        if (left < right) {
            return VersionComparatorResult.less_than;
        }
        return VersionComparatorResult.equals;
    }

    enum VersionComparatorResult {
        great_than,
        less_than,
        equals,
        unknown
    }
}
