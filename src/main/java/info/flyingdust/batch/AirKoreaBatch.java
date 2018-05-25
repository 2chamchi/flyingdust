package info.flyingdust.batch;


import info.flyingdust.model.ConfigFile;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.system.SystemProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * http://openapi.airkorea.or.kr/ 에서
 * 대기오염정보 조회 서비스 API 구현 (개발계정)
 */
public class AirKoreaBatch {

    private String BASE_REST_URL = "http://openapi.airkorea.or.kr/openapi/services/rest";

    // 대기 오염 정보 조회 서비스
    private String AIR_POLLUTION_INFO_INQUIRY_SERVICE_URL = "/ArpltnInforInqireSvc";

    // 측정소별 실시간 측정 정보 조회
    private String realtimeMeasureFromStation_URL = "/getMsrstnAcctoRltmMesureDnsty";

    //통합대기환경지수 나쁨 이상 측정소 목록 조회
    private String unityAirEnvironmentSensitiveStationList_URL = "/getUnityAirEnvrnIdexSnstiveAboveMsrstnList";

    //시도별 실시간 측정 정보 조회
    private String realtimeMeasureFromCity_URL = "/getCtprvnRltmMesureDnsty";

    // 대기질 예보통보 조회
    private String airPollutionForecast_URL = "/getMinuDustFrcstDspth";

    //시도별 실시간 평균정보 조회
    private String realtimeAverageInfoFromCity_URL = "/getCtprvnMesureLIst";

    //시군구별 실시간 평쥰 정보 조회
    private String realtimeAverageInfoFromTown_URL = "/getCtprvnMesureSidoLIst";

    private String serviceKey = ConfigFile.get("service.key");

    /**
     *
     * 측정소별 실시간 측정정보 조회
     *
     * @param numOfRows : 한 페이지 결과 수
     * @param pageNo : 페이지 번호
     * @param stationName : 측정소 명
     * @param dataTerm : 데이터 기간, DAILY(1일) / MONTH(1달) / 3MONTH(3달)
     * @param ver : 오퍼레이션 버전 1.0 <= <= 1.3
     * @return String : response Body
     * @throws IOException : byteStream 에러
     */
    public String getMsrstnAcctoRltmMesureDnsty(
            String numOfRows,
            String pageNo,
            String stationName,
            String dataTerm,
            String ver) throws IOException{


        String responseBody = "";
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_REST_URL)
                .append(AIR_POLLUTION_INFO_INQUIRY_SERVICE_URL)
                .append(realtimeMeasureFromStation_URL)
                .append("?")
                .append("stationName=").append(stationName)
                .append("&dataTerm=").append(dataTerm)
                .append("&pageNo=").append(pageNo)
                .append("&numOfRows=").append(numOfRows)
                .append("&ServiceKey=").append(serviceKey)
                .append("&ver=").append(ver)
                .append("&_returnType=json");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(sb.toString())
                .build();
        Response response = client.newCall(request).execute();
        if(response.isSuccessful()){
            responseBody = getResponseBody(response.body().byteStream());
        }
        return responseBody;
    }

    /**
     *
     * 통합대기환경지수 나쁨 이상 측정소 목록 조회
     *
     * @param numOfRows : 한 페이지 결과 수
     * @param pageNo : 페이지 번호
     * @return String : response Body
     * @throws IOException : byteStream 에러
     */
    public String getUnityAirEnvrnIdexSnstiveAboveMsrstnList(
            String numOfRows,
            String pageNo) throws IOException{
        String responseBody = "";
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_REST_URL)
                .append(AIR_POLLUTION_INFO_INQUIRY_SERVICE_URL)
                .append(unityAirEnvironmentSensitiveStationList_URL)
                .append("?")
                .append("&pageNo=").append(pageNo)
                .append("&numOfRows=").append(numOfRows)
                .append("&ServiceKey=").append(serviceKey)
                .append("&_returnType=json");

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(sb.toString())
                .build();

        Response response = client.newCall(request).execute();
        if(response.isSuccessful()){
            responseBody = getResponseBody(response.body().byteStream());
        }
        return responseBody;
    }


    /**
     *
     * 시도별 실시간 측정정보 조회
     *
     * @param numOfRows : 한 페이지 결과 수
     * @param pageNo : 페이지 번호
     * @param sidoName : 시도 명, 서울/부산/대구/인천/광주/대전/울산/경기/강원/충북/충남/전북/전남/경북/경남/제주/세종
     * @param ver : 오퍼레이션 버전 1.0 <= <= 1.3
     * @return String : response Body
     * @throws IOException : byteStream 에러
     */
    public String getCtprvnRltmMesureDnsty(
            String numOfRows,
            String pageNo,
            String sidoName,
            String ver) throws IOException{
        String responseBody = "";
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_REST_URL)
                .append(AIR_POLLUTION_INFO_INQUIRY_SERVICE_URL)
                .append(realtimeMeasureFromCity_URL)
                .append("?")
                .append("sidoName=").append(sidoName)
                .append("&pageNo=").append(pageNo)
                .append("&numOfRows=").append(numOfRows)
                .append("&ServiceKey=").append(serviceKey)
                .append("&ver=").append(ver)
                .append("&_returnType=json");

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(sb.toString())
                .build();

        Response response = client.newCall(request).execute();
        if(response.isSuccessful()){
            responseBody = getResponseBody(response.body().byteStream());
        }
        return responseBody;
    }


    /**
     *
     * 대기질 예보통보 조회
     *
     * @param numOfRows : 한 페이지 결과 수
     * @param pageNo : 페이지 번호
     * @param searchDate : 조회 날짜
     * @param informCode : 통보코드
     * @return String : response Body
     * @throws IOException : byteStream 에러
     */
    public String getMinuDustFrcstDspth(
            String numOfRows,
            String pageNo,
            String searchDate,
            String informCode) throws IOException{
        String responseBody = "";
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_REST_URL)
                .append(AIR_POLLUTION_INFO_INQUIRY_SERVICE_URL)
                .append(airPollutionForecast_URL)
                .append("?")
                .append("searchDate=").append(searchDate)
                .append("&pageNo=").append(pageNo)
                .append("&numOfRows=").append(numOfRows)
                .append("&ServiceKey=").append(serviceKey)
                .append("&InformCode=").append(informCode)
                .append("&_returnType=json");

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(sb.toString())
                .build();

        Response response = client.newCall(request).execute();
        if(response.isSuccessful()){
            responseBody = getResponseBody(response.body().byteStream());
        }
        return responseBody;
    }

    /**
     *
     * 시도별 실시간 평균정보 조회
     *
     * @param numOfRows : 한 페이지 결과 수
     * @param pageNo : 페이지 번호
     * @param itemCode : 항목명, SO2 / CO / O3 / NO2 / PM10 / PM25
     * @param dataGubun : 자료 구분, HOUR(시간평균) / DAILY(일평균)
     * @param searchCondition : 데이터 기간, WEEK(일주일) / MONTH(한달)
     * @return String : response Body
     * @throws IOException : byteStream 에러
     */
    public String getCtprvnMesureLIst(
            String numOfRows,
            String pageNo,
            String itemCode,
            String dataGubun,
            String searchCondition
    ) throws IOException{
        String responseBody = "";
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_REST_URL)
                .append(AIR_POLLUTION_INFO_INQUIRY_SERVICE_URL)
                .append(realtimeAverageInfoFromCity_URL)
                .append("?")
                .append("itemCode=").append(itemCode)
                .append("&dataGubun=").append(dataGubun)
                .append("&numOfRows=").append(numOfRows)
                .append("&pageNo=").append(pageNo)
                .append("&searchCondition=").append(searchCondition)
                .append("&ServiceKey=").append(serviceKey)
                .append("&_returnType=json");

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(sb.toString())
                .build();

        Response response = client.newCall(request).execute();
        if(response.isSuccessful()){
            responseBody = getResponseBody(response.body().byteStream());
        }
        return responseBody;
    }

    /**
     *
     * 시군구별 실시간 평균 조회
     *
     * @param numOfRows : 한 페이지 결과 수
     * @param pageNo : 페이지 번호
     * @param sidoName : 시도 명, 서울/부산/대구/인천/광주/대전/울산/경기/강원/충북/충남/전북/전남/경북/경남/제주/세종
     * @param searchCondition : 데이터 기간, HOUR(시간) / DAILY(하루)
     * @return String : response Body
     * @throws IOException : byteStream 에러
     */
    public String getCtprvnMesureSidoLIst(
            String numOfRows,
            String pageNo,
            String sidoName,
            String searchCondition) throws IOException{
        String responseBody = "";
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_REST_URL)
                .append(AIR_POLLUTION_INFO_INQUIRY_SERVICE_URL)
                .append(realtimeAverageInfoFromTown_URL)
                .append("?")
                .append("sidoName=").append(sidoName)
                .append("&searchCondition=").append(searchCondition)
                .append("&pageNo=").append(pageNo)
                .append("&numOfRows=").append(numOfRows)
                .append("&ServiceKey=").append(serviceKey)
                .append("&_returnType=json");

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(sb.toString())
                .build();

        Response response = client.newCall(request).execute();
        if(response.isSuccessful()){
            responseBody = getResponseBody(response.body().byteStream());
        }
        return responseBody;
    }

    /**
     *
     * http response 에서 body 데이터 추출
     *
     * @param inputStream
     * @return String : response Body
     * @throws IOException : byteStream 에러
     */
    private String getResponseBody(InputStream inputStream) throws IOException{
        StringBuilder responseBody = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line = br.readLine();
        while (line != null){
            responseBody.append(line);
            line = br.readLine();
        }
        br.close();
        return responseBody.toString();
    }

}
