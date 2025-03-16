package za.co.moson.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.co.moson.exceptions.MenuException;
import za.co.moson.models.Menu;
import za.co.moson.service.MenuService;

@RestController
@RequestMapping("/api/menus")
@CrossOrigin(origins = {"https://tastylovers.vercel.app", "http://localhost:4200/"})
public class MenuController {


    private final MenuService menuService;

    public MenuController(final MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/")
    ResponseEntity<Menu> createMenu(@RequestBody Menu menu) {
        return ResponseEntity.ok(this.menuService.create(menu));
    }

    @PutMapping("/")
    ResponseEntity<Menu> updateMenu(@RequestBody Menu menu) {
        return ResponseEntity.ok(this.menuService.update(menu));
    }

    @DeleteMapping("/")
    ResponseEntity<ResponseEntity.BodyBuilder> deleteMenu(@RequestBody Menu menu) {
        this.menuService.delete(menu);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/")
    ResponseEntity<Page<Menu>> findAllMenus(Pageable pageable) {
        return ResponseEntity.ok(this.menuService.findAllMenus(pageable));
    }


    @GetMapping("/{restaurantId}")
    ResponseEntity<Page<Menu>> findMenuByRestaurantId(@PathVariable Long restaurantId, Pageable pageable) {
        return ResponseEntity.ok(this.menuService.findByRestaurantId(restaurantId, pageable));
    }

    @PutMapping("/update-image")
    public ResponseEntity<Menu> updateRestaurant(@RequestParam(value = "file") MultipartFile multipartFile,
                                                 @RequestParam(value = "menuId") Long menuId) throws MenuException {
        return ResponseEntity.ok(this.menuService.update(multipartFile, menuId));
    }
}
