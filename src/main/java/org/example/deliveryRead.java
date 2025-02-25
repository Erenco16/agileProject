package org.example;

import java.util.ArrayList;

public class DeliveryAreaRead {

    /**
     * 通过配送区域名称查询具体的配送区域，并显示详情。
     *
     * @param name 配送区域的名称
     * @return 如果成功查询到配送区域则返回 true
     */
    public boolean selectDeliveryAreaMod(String name) {
        try {
            // 假设 DBClass.selectDeliveryArea(String name) 返回一个包含配送区域详情的二维 ArrayList，
            // 如果没有找到匹配的配送区域，则返回一个空的列表或 null。
            ArrayList<ArrayList<String>> deliveryArea = DBClass.selectDeliveryArea(name);

            if (deliveryArea == null || deliveryArea.isEmpty()) {
                throw new IllegalArgumentException("No name founded: " + name + " delivery area");
            } else {
                System.out.println("delivery area founded: " + deliveryArea);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error occurred: " + e.getMessage());
        }
        return true;
    }

    /**
     * 查询所有配送区域，并验证返回的配送区域列表有效（不为 null）。
     * 同时输出数据库中配送区域的详细信息。
     *
     * @return 如果成功查询则返回 true
     */
    public boolean selectAllDeliveryAreaMod() {
        try {
            ArrayList<ArrayList<String>> allDeliveryAreas = DBClass.selectAllDeliveryAreas();

            if (allDeliveryAreas == null) {
                throw new IllegalArgumentException("delivery area list is null");
            } else {
                System.out.println("delivery area list: " + allDeliveryAreas);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error occurred: " + e.getMessage());
        }
        return true;
    }

    /**
     * 根据配送区域名称查询配送区域，但不返回状态，只打印出异常信息。
     *
     * @param name 配送区域名称
     */
    public void selectDeliveryArea(String name) {
        try {
            DBClass.selectDeliveryArea(name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 查询所有配送区域，不返回状态，只打印出异常信息。
     */
    public void selectAllDeliveryArea() {
        try {
            DBClass.selectAllDeliveryAreas();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
