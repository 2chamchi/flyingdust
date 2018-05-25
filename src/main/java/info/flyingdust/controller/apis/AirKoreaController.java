package info.flyingdust.controller.apis;

import info.flyingdust.batch.AirKoreaBatch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Api(tags = "AirKoreaController", description = "미세먼지 조회 API")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/airkorea/api/v1/dust")
public class AirKoreaController {


    @ApiOperation(value = "측정소별 실시간 측정정보 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name="numOfRows", value ="한 페이지 결과 수", defaultValue = "10", paramType="query", dataType = "String", required = true),
            @ApiImplicitParam(name="pageNo", value ="페이지 번호", defaultValue="1", paramType="query", dataType = "String", required = true),
            @ApiImplicitParam(name="stationName", value ="측정소 명", defaultValue="서울", paramType="query", dataType = "String", required = true),
            @ApiImplicitParam(name="dataTerm", value ="데이터 기간", defaultValue="DAILY", paramType="query", dataType = "String", required = true),
            @ApiImplicitParam(name="version", value ="데이터 버전", defaultValue="1.3", paramType="query", dataType = "String", required = true)
    })
    @RequestMapping(
            value = "/1",
            method = {RequestMethod.GET},
            produces = "application/json")
    public ResponseEntity getMsrstnAcctoRltmMesureDnsty(
            @RequestParam(value = "numOfRows", defaultValue = "10") String numOfRows,
            @RequestParam(value = "pageNo", defaultValue = "1") String pageNo,
            @RequestParam(value = "stationName", defaultValue = "서울") String stationName,
            @RequestParam(value = "dataTerm", defaultValue = "DAILY") String dataTerm,
            @RequestParam(value = "version", defaultValue = "1.3") String version){

        AirKoreaBatch akb = new AirKoreaBatch();
        String response;
        ResponseEntity responseEntity;
        try{
            response = akb.getMsrstnAcctoRltmMesureDnsty(
                    numOfRows,
                    pageNo,
                    stationName,
                    dataTerm,
                    version);
            responseEntity = ResponseEntity.ok(response);
        }catch (IOException e){
            responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @ApiOperation(value = "통합대기환경지수 나쁨 이상 측정소 목록조회")
    @RequestMapping(
            value = "/2",
            method = {RequestMethod.GET},
            produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name="numOfRows", value ="한 페이지 결과 수", defaultValue = "10", paramType="query", dataType = "String", required = true),
            @ApiImplicitParam(name="pageNo", value ="페이지 번호", defaultValue="1", paramType="query", dataType = "String", required = true)
    })
    public ResponseEntity getUnityAirEnvrnIdexSnstiveAboveMsrstnList(
            @RequestParam(value = "numOfRows", defaultValue = "10") String numOfRows,
            @RequestParam(value = "pageNo", defaultValue = "1") String pageNo
    ) throws IOException{
        AirKoreaBatch akb = new AirKoreaBatch();
        String response = akb.getUnityAirEnvrnIdexSnstiveAboveMsrstnList(
                numOfRows,
                pageNo);

        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "시도별 실시간 측정정보 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name="numOfRows", value ="한 페이지 결과 수", defaultValue = "10", paramType="query", dataType = "String", required = true),
            @ApiImplicitParam(name="pageNo", value ="페이지 번호", defaultValue="1", paramType="query", dataType = "String", required = true),
            @ApiImplicitParam(name="cityName", value ="도시 이름", defaultValue="서울", paramType="query", dataType = "String", required = true),
            @ApiImplicitParam(name="version", value ="데이터 버전", defaultValue="1.3", paramType="query", dataType = "String", required = true)
    })
    @RequestMapping(
            value = "/3",
            method = {RequestMethod.GET},
            produces = "application/json")
    public ResponseEntity getCtprvnRltmMesureDnsty(
            @RequestParam(value = "numOfRows", defaultValue = "10") String numOfRows,
            @RequestParam(value = "pageNo", defaultValue = "1") String pageNo,
            @RequestParam(value = "cityName", defaultValue = "서울") String cityName,
            @RequestParam(value = "version", defaultValue = "1.3") String version
    ) throws IOException{
        AirKoreaBatch akb = new AirKoreaBatch();
        String response = akb.getCtprvnRltmMesureDnsty(
                numOfRows,
                pageNo,
                cityName,
                version);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "대기질 예보통보 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name="numOfRows", value ="한 페이지 결과 수", defaultValue = "10", paramType="query", dataType = "String", required = true),
            @ApiImplicitParam(name="pageNo", value ="페이지 번호", defaultValue="1", paramType="query", dataType = "String", required = true),
            @ApiImplicitParam(name="searchDate", value ="조회 날짜", defaultValue="2018-04-24", paramType="query", dataType = "String", required = true),
            @ApiImplicitParam(name="informCode", value ="통보코드", defaultValue="PM25", paramType="query", dataType = "String", required = true)
    })
    @RequestMapping(
            value = "/4",
            method = {RequestMethod.GET},
            produces = "application/json")
    public ResponseEntity getMinuDustFrcstDspth(
            @RequestParam(value = "numOfRows", defaultValue = "10") String numOfRows,
            @RequestParam(value = "pageNo", defaultValue = "1") String pageNo,
            @RequestParam(value = "searchDate") String searchDate,
            @RequestParam(value = "informCode") String informCode
    ) throws IOException{
        AirKoreaBatch akb = new AirKoreaBatch();
        String response = akb.getMinuDustFrcstDspth(
                numOfRows,
                pageNo,
                searchDate,
                informCode);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "시도별 실시간 평균정보 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name="numOfRows", value ="한 페이지 결과 수", defaultValue = "10", paramType="query", dataType = "String", required = true),
            @ApiImplicitParam(name="pageNo", value ="페이지 번호", defaultValue="1", paramType="query", dataType = "String", required = true),
            @ApiImplicitParam(name="itemCode", value ="항목명", defaultValue="PM10", paramType="query", dataType = "String", required = true),
            @ApiImplicitParam(name="dataClassify", value ="자료 구분", defaultValue="HOUR", paramType="query", dataType = "String", required = true),
            @ApiImplicitParam(name="searchCondition", value ="데이터 기간", defaultValue="WEEK", paramType="query", dataType = "String", required = true)
    })
    @RequestMapping(
            value = "/5",
            method = {RequestMethod.GET},
            produces = "application/json")
    public ResponseEntity getCtprvnMesureLIst(
            @RequestParam(value = "numOfRows", defaultValue = "10") String numOfRows,
            @RequestParam(value = "pageNo", defaultValue = "1") String pageNo,
            @RequestParam(value = "itemCode", defaultValue = "PM25") String itemCode,
            @RequestParam(value = "dataClassify", defaultValue = "HOUR") String dataClassify,
            @RequestParam(value = "searchCondition", defaultValue = "WEEK") String searchCondition
    ) throws IOException{
        AirKoreaBatch akb = new AirKoreaBatch();
        String response = akb.getCtprvnMesureLIst(
                numOfRows,
                pageNo,
                itemCode,
                dataClassify,
                searchCondition);
        return ResponseEntity.ok(response);
    }


    @ApiOperation(value = "시군구별 실시간 평균정보 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name="numOfRows", value ="한 페이지 결과 수", defaultValue = "10", paramType="query", dataType = "String", required = true),
            @ApiImplicitParam(name="pageNo", value ="페이지 번호", defaultValue="1", paramType="query", dataType = "String", required = true),
            @ApiImplicitParam(name="cityName", value ="시·도 명", defaultValue="서울", paramType="query", dataType = "String", required = true),
            @ApiImplicitParam(name="searchCondition", value ="데이터 기간", defaultValue="DAILY", paramType="query", dataType = "String", required = true)
    })
    @RequestMapping(
            value = "/6",
            method = {RequestMethod.GET},
            produces = "application/json")
    public ResponseEntity getCtprvnMesureSidoLIst(
            @RequestParam(value = "numOfRows", defaultValue = "10") String numOfRows,
            @RequestParam(value = "pageNo", defaultValue = "1") String pageNo,
            @RequestParam(value = "cityName", defaultValue = "서울") String cityName,
            @RequestParam(value = "searchCondition", defaultValue = "HOUR") String searchCondition
    ) throws IOException{
        AirKoreaBatch akb = new AirKoreaBatch();
        String response = akb.getCtprvnMesureSidoLIst(
                numOfRows,
                pageNo,
                cityName,
                searchCondition);
        return ResponseEntity.ok(response);
    }

}
