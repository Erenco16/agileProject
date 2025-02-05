package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // 模拟存储已创建的配送区域
    private static List<DeliveryArea> deliveryAreaList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // --- 预先创建一个配送区域，方便后续更新测试 ---
        DeliveryArea area = new DeliveryArea();
        area.name = "Dublin";
        area.description = "Original Description";
        deliveryAreaList.add(area);

        System.out.println("当前配送区域列表：");
        displayDeliveryAreas();


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
            for (DeliveryArea area1 : deliveryAreaList) {
                if (area1 != null) {
                    System.out.println("Name：" + area1.name + "，Description：" + area1.description);
                }
            }
        } else {
            System.out.println("delivery area list is empty。");
        }

        // --- 更新配送区域的流程 ---
        // 1. 根据名称查找需要更新的配送区域
        System.out.print("\nPlease enter the updating name of the delivery area to be updated: ");
        String updateName = scanner.nextLine();
        DeliveryArea updateArea = findDeliveryAreaByName(updateName);

        // 验证该配送区域是否存在（不为 null）
        if (updateArea == null) {
            System.out.println("Delivery is not existed and cant update。");
            return;
        }

        // 2. 提示当前描述，并要求用户输入新的描述
        System.out.println("Current description: " + updateArea.description);
        System.out.print("please enter new description (1-1024字符): ");
        String newDescription = scanner.nextLine();

        // 验证更新的描述是否合法（字符长度在1到1024之间）
        if (newDescription == null || newDescription.isEmpty() || newDescription.length() > 1024) {
            System.out.println("invalid description， valid characters between 1-1024");
            return;
        }

        // 3. 更新描述，并模拟保存到数据库（内存列表）
        updateArea.description = newDescription;
        System.out.println("successfully updated delivery description！");

        // 4. 读取并显示配送区域列表，验证更新已成功反映
        System.out.println("\nnew delivery list ：");
        displayDeliveryAreas();

        // --- 删除配送区域的流程 ---
        System.out.print("\nplease enter the  deleting name of the new delivery area to be updated: ");
        String deleteName = scanner.nextLine();

        // 根据名称查找待删除的配送区域
        DeliveryArea areaToDelete = findDeliveryAreaByName(deleteName);
        if (areaToDelete == null) {
            System.out.println("delivery area is not existed，stop operating。");
        } else {
            // 删除操作
            deliveryAreaList.remove(areaToDelete);
            System.out.println("delivery area '" + deleteName + "' deleted。");

            // 验证删除结果：再次查找，确保返回 null
            if (findDeliveryAreaByName(deleteName) == null) {
                System.out.println("successfully deleted ：delivery area '" + deleteName + "' not exist。");
            } else {
                System.out.println("error：delivery area '" + deleteName + "' still exist。");
            }
        }

        // 显示更新后的配送区域列表
        System.out.println("\nUpdated delivery Area List：");
        displayDeliveryAreas();
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

    /**
     * 根据名称查找配送区域
     * @param name 配送区域名称
     * @return 如果存在则返回该配送区域，否则返回 null
     */
    private static DeliveryArea findDeliveryAreaByName(String name) {
        for (DeliveryArea area : deliveryAreaList) {
            if (area.name.equals(name)) {
                return area;
            }
        }
        return null;
    }

    /**
     * 显示配送区域列表中的所有区域详细信息
     */
    private static void displayDeliveryAreas() {
        if (deliveryAreaList != null && !deliveryAreaList.isEmpty()) {
            for (DeliveryArea area : deliveryAreaList) {
                if (area != null) {
                    System.out.println("name: " + area.name + ", description: " + area.description);
                }
            }
        } else {
            System.out.println("没有任何配送区域。");
        }
    }
}

