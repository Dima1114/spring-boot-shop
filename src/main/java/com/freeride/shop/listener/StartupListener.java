package com.freeride.shop.listener;

import com.freeride.shop.entity.*;
import com.freeride.shop.repository.*;
import com.freeride.shop.service.ItemAvailabilityService;
import com.freeride.shop.service.ItemService;
import com.freeride.shop.service.RoleService;
import com.freeride.shop.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class StartupListener implements ApplicationListener<ApplicationReadyEvent> {
    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;
    private BrandRepository brandRepository;
    private SizeRepository sizeRepository;
    private ItemAvailabilityService itemAvailabilityService;
    private UserService userService;
    private UserRepository userRepository;
    private RoleService roleService;
    private ItemService itemService;
    private String imagesPath;


    public StartupListener(ItemRepository itemRepository, CategoryRepository categoryRepository,
                           BrandRepository brandRepository, SizeRepository sizeRepository, ItemAvailabilityService itemAvailabilityService, UserService userService, UserRepository userRepository, RoleService roleService, ItemService itemService,
                           @Value("${images.item.path}") String imagesPath) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.sizeRepository = sizeRepository;
        this.itemAvailabilityService = itemAvailabilityService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.itemService = itemService;
        this.imagesPath = imagesPath;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        //set admin role to admin user
        User admin = userService.getUserByName("");
        Role adminRole = roleService.getRole("admin");
        Role userRole = roleService.getRole("user");
        admin.setRoles(Arrays.asList(userRole, adminRole));
        userRepository.save(admin);

        //set users role
        User user = userService.getUserByName("user");
        user.setRoles(Collections.singletonList(userRole));
        userRepository.save(user);

        //brand - item binding
        List<Brand> brands = brandRepository.findAll();
        List<Item> items = itemRepository.findAll();

        items.forEach(item ->
                brands.stream()
                        .filter(brand ->
                                item.getName().contains(brand.getName()))
                        .findAny().ifPresent(brand -> {
                    item.setBrand(brand);
                    itemRepository.save(item);
                }));

        //size - category binding
        List<Category> categories = categoryRepository.findAll();

        categories.stream().filter(s -> "Jackets".equals(s.getName()) || "Pants".equals(s.getName()))
                .forEach(cat -> cat.setSizeType("Clothes"));
        categories.stream().filter(s -> "Bindings".equals(s.getName()))
                .forEach(cat -> cat.setSizeType("Bindings"));
        categories.stream().filter(s -> "Boots".equals(s.getName()))
                .forEach(cat -> cat.setSizeType("Boots"));
        categories.stream().filter(s -> "Snowboards".equals(s.getName()))
                .forEach(cat -> cat.setSizeType("Snowboards"));
        categories.stream().filter(s -> "Masks".equals(s.getName()))
                .forEach(cat -> cat.setSizeType("UniSize"));

        categoryRepository.save(categories);

        //addItem sizes to items
        List<Size> sizes = sizeRepository.findAll();

        items = itemRepository.findAll();
        for (Item item : items) {
            Collections.shuffle(sizes);

            for (int i = 1; i < 11; i++) {
                Size size = sizes.get(i);
                if(!size.getType().equals(item.getCategory().getSizeType())){
                    continue;
                }
                ItemAvailability itemAvailability = new ItemAvailability();
                itemAvailability.setItem(item);
                itemAvailability.setSize(size);
                itemAvailability.setQuantity(i < 3 ? i : i / 3);

                itemAvailabilityService.saveAvailability(itemAvailability);
            }
        }


        //getImage saving
//            String[] tokens = item.getName().split(" ");
//            StringBuilder imgName = new StringBuilder();
//            for (String token : tokens) {
//                imgName.append("-").append(token);
//            }
//
//            Path path = Paths.getCategory("../shop-images/source2/" + item.getCategory().getName().toLowerCase(),
//                    imgName.toString().toLowerCase() + ".jpg");
//            Path path2 = Paths.getCategory("../shop-images/source2/" + item.getCategory().getName().toLowerCase(),
//                    imgName.toString().toLowerCase() + "-2018" + ".jpg");
//
//            byte[] img = null;
//            try {
//                img = Files.readAllBytes(path);
//                System.out.println("Img was read");
//            } catch (IOException e) {
//                try {
//                    img = Files.readAllBytes(path2);
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//
//            if (img != null) {
//                itemService.saveImage(item, img);
//            }


    }

}