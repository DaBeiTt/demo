package so.wwb.api;

import org.apache.commons.collections4.CollectionUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 測試各功能專用
 */
public class CustomToolBox {

    public Integer add(int a, int b) {
        return a + b;
    }

    /**
     * 隨機 UUID
     */
    public static String generatePlayerId() {
        String randomId = UUID.randomUUID().toString().replace("-","");
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
     * @param stamp 時間戳
     * @param zone 時區
     */
    public static String timestampToDate(Long stamp, String zone) {
        ZonedDateTime dateTime = Instant.ofEpochMilli(stamp).atZone(ZoneId.of(zone));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formattedDate = dateTime.format(formatter);
        return "台北時間： " + formattedDate;
    }

    /**
     * 計算 HMAC-SHA256 簽名
     * @param message 預加密訊息
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
}
