package com.example.take_home_java_billing.Controller;

import com.example.take_home_java_billing.Service.FaturamentoPeriodService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/periodos")
public class FaturamentoPeriodController {
    private final FaturamentoPeriodService service;

    public FaturamentoPeriodController(FaturamentoPeriodService service) {

        this.service = service;
    }

    // GET /periodos/{ano}
    @GetMapping("/{ano}")
    @Operation(summary = "Formato do Ano: yyyy")
    public List<FaturamentoPeriodService.Periodo> listarPeriodosPorAno(@PathVariable int ano) {
        return service.getPeriodosDoAno(ano);
    }

    // GET /periodos/periodo?data=yyyy-MM-dd
    @GetMapping("/periodo")
    @Operation(summary = "Formato da Data: yyyy-MM-dd")
    public Map<String, String> obterPeriodIdPorData(@RequestParam String data) {
        try {
            LocalDate localDate = LocalDate.parse(data);
            String periodId = service.getPeriodId(localDate);
            return Collections.singletonMap("periodId", periodId);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de data inválido. Use yyyy-MM-dd.");
        }
    }
    @GetMapping("/lista")
    @Operation(summary = "Formato da Data: yyyy-MM-dd")
    public Map<String, String> listaPeriodIdPorData(@RequestParam String data) {
        try {
            LocalDate localDate = LocalDate.parse(data);
            String periodId = service.getPeriodId(localDate);
            return Collections.singletonMap( periodId, data);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de data inválido. Use yyyy-MM-dd.");
        }
    }
    @PostMapping("/periodos-datas")
    public ResponseEntity<List<String>> obterPeriodosParaMultiplasDatas(@RequestBody List<String> datas)
    {
        List<LocalDate> datasValidas = new ArrayList<>();
        List<String> erros = new ArrayList<>();

        for (String dataStr : datas) {
            try {
                datasValidas.add(LocalDate.parse(dataStr));
            } catch (DateTimeParseException e) {
                erros.add("Data inválida: " + dataStr);
            }
        }

        List<String> resultado = service.calcularPeriodosPorDatas(datasValidas);
        if (!erros.isEmpty()) {
            resultado.addAll(erros); // ou envie separadamente se quiser mais controle
        }

        return ResponseEntity.ok(resultado);
    }
}
