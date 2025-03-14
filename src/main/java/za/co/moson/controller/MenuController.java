package za.co.moson.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.moson.models.Menu;
import za.co.moson.service.MenuService;

@RestController
@RequestMapping("/api/menus")
public class MenuController {


    private final MenuService menuService;

    public MenuController(final MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/")
    ResponseEntity<Menu> createMenu(@RequestBody Menu menu, @RequestParam String zoneId) {
        return ResponseEntity.ok(this.menuService.create(menu, zoneId));
    }

    @PutMapping("/")
    ResponseEntity<Menu> updateMenu(@RequestBody Menu menu, @RequestParam String zoneId) {
        return ResponseEntity.ok(this.menuService.update(menu, zoneId));
    }

    @DeleteMapping("/")
    ResponseEntity<Menu> deleteMenu(@RequestBody Menu menu, @RequestParam String zoneId) {
        return ResponseEntity.ok(this.menuService.create(menu, zoneId));
    }

    @GetMapping("/{restaurantId}")
    ResponseEntity<Page<Menu>> findMenuByRestaurantId(@PathVariable Long restaurantId, Pageable pageable) {
        return ResponseEntity.ok(this.menuService.findByRestaurantId(restaurantId, pageable));
    }
}
