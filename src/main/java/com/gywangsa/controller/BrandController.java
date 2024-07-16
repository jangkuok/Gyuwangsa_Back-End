package com.gywangsa.controller;

import com.gywangsa.dto.*;
import com.gywangsa.service.BrandService;
import com.gywangsa.service.LikeChkService;
import com.gywangsa.service.OrderService;
import com.gywangsa.service.PdInfoService;
import com.gywangsa.util.CustomFileUtil;
import com.gywangsa.util.CustomFileUtilBrand;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/brand")
public class BrandController {

    private final BrandService brandService;

    private final PdInfoService pdInfoService;

    private final OrderService orderService;

    private final LikeChkService likeChkService;

    private final CustomFileUtilBrand customFileUtilBrand;

    //상품 이미지
    @GetMapping("/view/{fileNm}")
    public ResponseEntity<Resource> viewFile(@PathVariable("fileNm") String fileNm) {
        log.info("-------------------BrandController-------------------");
        log.info("============이미지 불러오기============");
        return customFileUtilBrand.selectByFile(fileNm);
    }

    //브랜드 조회
    @GetMapping("/info/{brandNo}")
    public BrandDTO selectBrandByBrandNo(@PathVariable("brandNo") Long brandNo) {
        log.info("-------------------BrandController-------------------");
        log.info("============브랜드 조회============");

        BrandDTO brandDTO = brandService.selectBrandByBrandNo(brandNo);
        log.info(brandDTO);

        return brandDTO;
    }

    //브랜드 리스트 조회
    @GetMapping("/list")
    public PageResponseDTO<BrandDTO> selectBrandList(PageRequestDTO pageRequestDTO) {
        log.info("-------------------BrandController-------------------");
        log.info("============브랜드 리스트 조회============");
        return brandService.selectBrandList(pageRequestDTO);
    }

    //브랜드 등록
    @PostMapping("/addBrand")
    public Map<String, Long> insertBrand(@RequestPart(value = "brandDTO") BrandDTO brandDTO,
                                         @RequestPart(value = "logFile", required = false) MultipartFile logFile,
                                         @RequestPart(value = "mainFile", required = false) MultipartFile mainFile
    ) {
        log.info("-------------------BrandController-------------------");
        log.info("============브랜드 등록============");

        String fileName = customFileUtilBrand.saveBrandFile(logFile);
        brandDTO.setBrandLog(fileName);

        if (mainFile != null) {
            String mainFileName = customFileUtilBrand.saveBrandFile(mainFile);
            brandDTO.setBrandMainImage(mainFileName);
        } else if (mainFile == null) {
            brandDTO.setBrandMainImage("");
        }


        LocalDateTime now = LocalDateTime.now();
        brandDTO.setStartDate(now);
        brandDTO.setEndDate(now.plusMonths(24));

        Long brandNo = brandService.insertBrand(brandDTO);

        return Map.of("brandNo", brandNo);

    }

    //브랜드 수정
    @PutMapping("/modifyBrand")
    public Long modifyBrand(@RequestPart(value = "brandDTO") BrandDTO brandDTO,
                            @RequestPart(value = "logFile", required = false) MultipartFile logFile,
                            @RequestPart(value = "mainFile", required = false) MultipartFile mainFile) {
        log.info("-------------------BrandController-------------------");
        log.info("============브랜드 수정============");

        BrandDTO dto = brandService.selectBrandByBrandNo(brandDTO.getBrandNo());

        if (brandDTO.getBrandLog() == "") {
            customFileUtilBrand.deleteFiles(dto.getBrandLog());
        }

        if (brandDTO.getBrandMainImage() == "") {
            customFileUtilBrand.deleteFiles(dto.getBrandMainImage());
        }

        if (logFile != null) {
            String fileName = customFileUtilBrand.saveBrandFile(logFile);
            brandDTO.setBrandLog(fileName);
            log.info(logFile);
        }
        if (mainFile != null) {
            String mainFileName = customFileUtilBrand.saveBrandFile(mainFile);
            brandDTO.setBrandMainImage(mainFileName);
            log.info(mainFile);
        }

        Long brandNo = brandService.modifyBrand(brandDTO);

        return brandNo;
    }

    //목록 조회
    @GetMapping("/pdInfoList/{brandNo}")
    public PageResponseDTO<PdInfoDTO> selectListByBrandPdInfo(PageRequestDTO pageRequestDTO,
                                                              @PathVariable("brandNo") Long brandNo
                                                            , @RequestParam("userId") String userId) {
        log.info("-------------------BrandController-------------------");
        log.info("============브랜드 전체 리스트 조회============");

        PageResponseDTO<PdInfoDTO> pdInfoDTOPageResponseDTO = pdInfoService.selectListByBrandPdInfo(pageRequestDTO, brandNo);

        List<LikeChkDTO> dtoList = likeChkService.selectUserIdLikeChk(userId);

        if(userId != null || !userId.equals("")){
            for(int i = 0; i < dtoList.size();i++){
                for(int j = 0; j < pdInfoDTOPageResponseDTO.getDtoList().size(); j++){
                    if(dtoList.get(i).getPdNo().equals(pdInfoDTOPageResponseDTO.getDtoList().get(j).getPdNo())){
                        pdInfoDTOPageResponseDTO.getDtoList().get(j).setLikeFlag(true);
                        log.info(pdInfoDTOPageResponseDTO.getDtoList().get(j));
                    }
                }
            }
        }
        return pdInfoDTOPageResponseDTO;
    }

    //브랜드 카테고리 조회
    @GetMapping("/pdInfoList/{brandNo}/{categoryNo}")
    public PageResponseDTO<PdInfoDTO> selectListByBrandCategory(PageRequestDTO pageRequestDTO,
                                                                @PathVariable("brandNo") Long brandNo,
                                                                @PathVariable("categoryNo") Long categoryNo
                                                              , @RequestParam("userId") String userId) {
        log.info("-------------------BrandController-------------------");
        log.info("============브랜드 카테고리 조회============");

        PageResponseDTO<PdInfoDTO> pdInfoDTOPageResponseDTO = pdInfoService.selectListByBrandCategory(pageRequestDTO, categoryNo, brandNo);

        List<LikeChkDTO> dtoList = likeChkService.selectUserIdLikeChk(userId);

        if(userId != null || !userId.equals("")){
            for(int i = 0; i < dtoList.size();i++){
                for(int j = 0; j < pdInfoDTOPageResponseDTO.getDtoList().size(); j++){
                    if(dtoList.get(i).getPdNo().equals(pdInfoDTOPageResponseDTO.getDtoList().get(j).getPdNo())){
                        pdInfoDTOPageResponseDTO.getDtoList().get(j).setLikeFlag(true);
                        log.info(pdInfoDTOPageResponseDTO.getDtoList().get(j));
                    }
                }
            }
        }

        return pdInfoDTOPageResponseDTO;
    }


    //브랜드 카테고리 아이템 조회
    @GetMapping("/pdInfoList/{brandNo}/{categoryNo}/{itemNo}")
    public PageResponseDTO<PdInfoDTO> selectListByBrandCategoryItem(PageRequestDTO pageRequestDTO,
                                                                    @PathVariable("brandNo") Long brandNo,
                                                                    @PathVariable("itemNo") Long itemNo,
                                                                    @PathVariable("categoryNo") Long categoryNo
                                                                  , @RequestParam("userId") String userId) {
        log.info("-------------------BrandController-------------------");
        log.info("============브랜드 카테고리 아이템 조회============");

        PageResponseDTO<PdInfoDTO> pdInfoDTOPageResponseDTO = pdInfoService.selectListByBrandCategoryItem(pageRequestDTO, categoryNo, itemNo, brandNo);

        List<LikeChkDTO> dtoList = likeChkService.selectUserIdLikeChk(userId);

        if(userId != null || !userId.equals("")){
            for(int i = 0; i < dtoList.size();i++){
                for(int j = 0; j < pdInfoDTOPageResponseDTO.getDtoList().size(); j++){
                    if(dtoList.get(i).getPdNo().equals(pdInfoDTOPageResponseDTO.getDtoList().get(j).getPdNo())){
                        pdInfoDTOPageResponseDTO.getDtoList().get(j).setLikeFlag(true);
                        log.info(pdInfoDTOPageResponseDTO.getDtoList().get(j));
                    }
                }
            }
        }

        return pdInfoDTOPageResponseDTO;
    }

    //특정 브랜드 주문 목록
    @GetMapping("/orderList/{brandNo}")
    public PageResponseDTO<OrderDtlDTO> selectBrandOrderList(PageRequestDTO pageRequestDTO,
                                                             @PathVariable("brandNo") Long brandNo) {
        log.info("-------------------OrderController-------------------");
        log.info("============브랜드 주문 리스트 조회============");
        log.info(orderService.selectBrandOrderList(pageRequestDTO, brandNo));

        return orderService.selectBrandOrderList(pageRequestDTO, brandNo);
    }

    //특정 브랜드 주문상태 목록
    @GetMapping("/orderList/{brandNo}/{deliStatus}")
    public PageResponseDTO<OrderDtlDTO> selectBrandOrderDeliStatus(PageRequestDTO pageRequestDTO,
                                                                   @PathVariable("brandNo") Long brandNo,
                                                                   @PathVariable("deliStatus") String deliStatus) {
        log.info("-------------------OrderController-------------------");
        log.info("============브랜드 주문 상태 리스트 조회============");
        log.info(orderService.selectBrandOrderDeliStatus(pageRequestDTO, brandNo, deliStatus));

        return orderService.selectBrandOrderDeliStatus(pageRequestDTO, brandNo, deliStatus);
    }

    //브랜드 키워드 검색
    @GetMapping("/search/{keyword}")
    public PageResponseDTO<BrandDTO> selectBrandByKeyword(PageRequestDTO pageRequestDTO,
                                                          @PathVariable("keyword") String keyword) {
        log.info("-------------------OrderController-------------------");
        log.info("============브랜드 키워드 조회============");
        log.info(brandService.selectBrandByKeyword(pageRequestDTO, keyword));

        return brandService.selectBrandByKeyword(pageRequestDTO, keyword);
    }
}

