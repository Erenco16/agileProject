package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // 模拟存储已创建的配送区域
    private static List<DeliveryArea> deliveryAreaList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // get input:name
        System.out.print("please enter delivery name: ");
        String inputName = scanner.nextLine();

        // get input: description
        System.out.print("please  enter description: ");
        String inputDescription = scanner.nextLine();

        // 创建配送区域对象，并赋值
        DeliveryArea newArea = new DeliveryArea();
        newArea.name = inputName;
        newArea.description = inputDescription;

        // 先验证输入是否符合长度要求
        if (!newArea.testName()) {
            System.out.println("invalid name， valid  name is between 1-255 characters");
            return;
        }
        if (!newArea.testDescription()) {
            System.out.println("invalid description，valid inputs is between 1-1024 characters");
            return;
        }

        // 验证该配送区域是否已存在（根据名称判断）
        if (areaAlreadyExists(newArea)) {
            System.out.println("the delivery area already exist，dont create。");
        } else {
            // 不存在则添加到列表中
            deliveryAreaList.add(newArea);
            System.out.println("create delivery area successfully！");
        }
        if (deliveryAreaList != null && !deliveryAreaList.isEmpty()) {
            System.out.println("Current delivery area list：");
            for (DeliveryArea area : deliveryAreaList) {
                if (area != null) {
                    System.out.println("Name：" + area.name + "，Description：" + area.description);
                }
            }
        } else {
            System.out.println("配送区域列表为空。");
        }
    }


    /**
     * 检查内存中是否已存在相同名称的配送区域
     * @param newArea 新的配送区域
     * @return 如果已存在返回 true，否则返回 false
     */
    private static boolean areaAlreadyExists(DeliveryArea newArea) {
        for (DeliveryArea area : deliveryAreaList) {
            // 假设名称相同即视为已存在
            if (area.name.equals(newArea.name)) {
                return true;
            }
        }
        return false;
    }
}

