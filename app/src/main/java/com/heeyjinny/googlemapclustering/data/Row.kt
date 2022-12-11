package com.heeyjinny.googlemapclustering.data

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

//++1
//마커에 해당하는 클래스가 Row클래스 이기 때문에
//클러스터아이템 상속 받은 후
//좌표를 반환하는 함수와 부가 정보를 반환하는 함수들 구현
data class Row(
    val ADRES: String,
    val CODE_VALUE: String,
    val FDRM_CLOSE_DATE: String,
    val GU_CODE: String,
    val HMPG_URL: String,
    val LBRRY_NAME: String,
    val LBRRY_SEQ_NO: String,
    val LBRRY_SE_NAME: String,
    val OP_TIME: String,
    val TEL_NO: String,
    val XCNTS: String,
    val YDNTS: String
): ClusterItem {
    //++2
    //데이터 클래스에 클러스터 아이템을 추가하고 필수 메서드 오버라이드

    //++3
    //개별 마커가 표시될 좌표
    override fun getPosition(): LatLng {
        return LatLng(XCNTS.toDouble(), YDNTS.toDouble())
    }

    //++4
    //마커를 클릭했을 때 나타나는 타이틀
    override fun getTitle(): String? {
        return LBRRY_NAME
    }

    //++5
    //마커를 클릭했을 때 나타나는 서브 타이틀
    override fun getSnippet(): String? {
        return ADRES
    }

    //++6
    //id에 해당하는 유일한 값을 Int로 반환
    //값 중에 null이 있을 경우 hashCoda생성 시 오류 발생
    override fun hashCode(): Int {
        return LBRRY_SEQ_NO.toInt()
    }

}

//++7
//앱이 실행되고 지도에 마커표시가 될 때
//안드로이드는 Row클래스의 getPosition()메서드 호출
//해당 마커의 좌표 계산한 뒤
//특정 범위 안에 있는 마커들을 묶어 하나의 마커로 만들고
//몇 개의 마커가 포함되어 있는지 숫자로 표시함
//클러스터 매니저를 통해 클러스터 사용...
//MapsActivity.kt 에 선언