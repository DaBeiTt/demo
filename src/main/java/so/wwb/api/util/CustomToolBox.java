package so.wwb.api.util;

import org.apache.commons.collections4.CollectionUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 測試各功能專用
 */
public class CustomToolBox {

    public static String getIPv4(Long ip) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            long l = ip >> (3 - i) * 8 & 0xFF;
            sb.append(l).append(".");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static LocalDateTime convertToPlatformTime(long ticks) {
        long millisSinceUnixEpoch = (ticks - 621355968000000000L) / 10000;
        return Instant.ofEpochMilli(millisSinceUnixEpoch).atZone(ZoneOffset.UTC).toLocalDateTime();
    }

    public static boolean isZero(String num) {
        return Double.parseDouble(num) == 0;
    }

    public static String getPrefix(String statement, String target) {
        Pattern pattern = Pattern.compile(statement);
        Matcher matcher = pattern.matcher(target);
        return matcher.find() ? matcher.group(1) : "Not Found";
    }

    public static <T> T getObject(Supplier<T> supplier) {
        return supplier.get();
    }

    /**
     * item.get 的參數要換成自己 List 的屬性
     * 從 List 中根據 betType 獲取需要的 betItem
     *
     * @return betItem 的值
     */
    private static String findValue(List<Map<String, String>> itemList, String keyword) {

        return itemList.stream()
                .filter(item -> keyword.equals(item.get("betType")))
                .map(item -> item.get("betItem"))
                .findFirst()
                .orElse(null);
    }

    /**
     * 隨機 UUID
     */
    public static String generatePlayerId() {
        String randomId = UUID.randomUUID().toString().replace("-", "");
        return "robot_" + randomId;
    }

    /**
     * 取出兩個 List 不重複的值
     */
    public static List<String> disJunction() {
        List<String> list1 = Arrays.asList("apple", "banana", "guava");
        List<String> list2 = Arrays.asList("apple", "pineapple", "strawberry");

        return new ArrayList<>(CollectionUtils.disjunction(list1, list2));
    }

    /**
     * 時間戳轉換為 Date
     *
     * @param stamp 時間戳
     * @param zone  時區
     */
    public static String timestampToDate(Long stamp, String zone) {
        ZonedDateTime dateTime = Instant.ofEpochMilli(stamp).atZone(ZoneId.of(zone));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formattedDate = dateTime.format(formatter);
        return "台北時間： " + formattedDate;
    }

    /**
     * 計算 HMAC-SHA256 簽名
     *
     * @param message   預加密訊息
     * @param secretKey 密鑰
     */
    public static String calculateHMAC(String message, String secretKey) throws Exception {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

        Mac cryptographer = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "HmacSHA256");
        cryptographer.init(secretKeySpec);

        byte[] bytes = cryptographer.doFinal(messageBytes);
        StringBuilder hexStringBuilder = new StringBuilder();
        for (byte b : bytes) {
            hexStringBuilder.append(String.format("%02x", b));
        }
        return hexStringBuilder.toString();
    }

    /**
     * 生成範圍內隨機 Double
     *
     * @param min 最小值
     * @param max 最大值
     * @return 去小數點之 Double
     */
    public static Double generateRandomProfitLossInRange(double min, double max) {

        Random random = new Random();

        if (min > max) {
            throw new IllegalArgumentException("範圍錯誤");
        }

        return Math.floor(200 + (800 * random.nextDouble()));
    }

    public static Integer add(int a, int b) {
        return a + b;
    }
}
