package com.nenaddj.dib.controller;

import com.nenaddj.dib.model.entity.BeerEntity;
import com.nenaddj.dib.model.mapper.BeerMapper;
import com.nenaddj.dib.service.BeerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/beers")
public class BeerController {

    private final BeerService beerService;
    private final BeerMapper beerMapper;

    public BeerController(BeerService beerService, BeerMapper beerMapper) {
        this.beerService = beerService;
        this.beerMapper = beerMapper;
    }

    @GetMapping
    public ResponseEntity<Page<com.nenaddj.dib.model.dto.BeerDTO>> getAll(
            @PageableDefault(size = 50)
            @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(BeerMapper.toPageDto(beerService.findAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.nenaddj.dib.model.dto.BeerDTO> getBeer(@PathVariable Long id){
        try{
            BeerEntity beer = beerService.findById(id);

            if (null == beer) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return ResponseEntity.ok(BeerMapper.entityToDto(beer));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try {
            beerService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
// TODO correct the call, return type
    @PostMapping("/{count}")
    public ResponseEntity<Integer> insertBeers(@PathVariable Integer count){
        try {
            beerService.insertBeers(count);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(count, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
