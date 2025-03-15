package com.dio.design_patterns.config;

import com.dio.design_patterns.entity.PurchOrder;
import com.dio.design_patterns.entity.User;
import com.dio.design_patterns.entity.enums.Role;
import com.dio.design_patterns.service.PurchOrderService;
import com.dio.design_patterns.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final PurchOrderService purchOrderService;

    static final List<User> users = new ArrayList<>(List.of(
            new User("bruce.wayne", "123", Role.DIRECTOR),
            new User("clark.kent", "crypto", Role.MANAGER),
            new User("arthur.fleck", "batmanS2", Role.ANALIST),
            new User("dick.grayson", "robin", Role.ASSISTANT)));

    static final List<PurchOrder> purchaseOrders = new ArrayList<>(List.of(
            new PurchOrder("Materiais de Escritório", BigDecimal.valueOf(8990)),
            new PurchOrder("Cadeiras Ergonomicas", BigDecimal.valueOf(15000)),
            new PurchOrder("Serviço de TI - Manutenção de Servidores", BigDecimal.valueOf(48000)),
            new PurchOrder("Compra de Notebooks", BigDecimal.valueOf(32000)),
            new PurchOrder("Mesa de Reunião", BigDecimal.valueOf(8700)),
            new PurchOrder("Papelaria e Insumos", BigDecimal.valueOf(5600)),
            new PurchOrder("Upgrade de Rede - Switches e Roteadores", BigDecimal.valueOf(72000)),
            new PurchOrder("Software de Gestão", BigDecimal.valueOf(25000)),
            new PurchOrder("Café e Snacks para Funcionários", BigDecimal.valueOf(980)),
            new PurchOrder("Material de Limpeza", BigDecimal.valueOf(4600)),
            new PurchOrder("Treinamento Corporativo", BigDecimal.valueOf(28000))
    ));

    public DataLoader(UserService userService, PurchOrderService purchOrderService) {
        this.userService = userService;
        this.purchOrderService = purchOrderService;
    }

    @Override
    public void run(String... args) throws Exception {
        users.forEach(userService::save);
        purchaseOrders.forEach(purchOrderService::save);
    }
}
