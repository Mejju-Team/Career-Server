package com.example.career.domain.consult.Dto;

import com.example.career.domain.consult.Entity.Query;
import com.example.career.domain.user.Dto.MenteeRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultRespDto {
    private Long consultId;
    private String zoomLink;
    private String major;
    private MenteeRespDto menteeRespDto;
        private Query query;

//    {
//    "success": true,
//    "status": 200,
//    "message": "요청에 성공하였습니다.",
//    "Object": {
//        "consultId": 1, // 상담 고유 id
//				"tutorResponse": {   // 학생에게 보여지지 않음 이 부분은 회사+본인만 열람 가능
//								"flow" : "이 학생은 ~~ 부분에 있어서 초점을 맞추어 수업을 진행,장점을"
//								"query1" : "튜터한테 궁금한 것에 대한 답변",
//								"query2" : "학과에 대해 궁금한 것에 대한 답변",
//								"etc": "자유롭게 추가적인 질문에 대한 답변"
//
//				},  // 튜터가 상담 진행 할 내용
//				"zoomLink":"https:zoom.31rffkwa.com", // 줌링크
//				"student": {   // 학생 정보
//							"stuId":32,
//							"nickname" : "종두",
//							"gender" : "true",
//							"age" : "18",
//							"profileImg":"https://"
//							"stuUrl":"https//domain.com/showInfo/user/username"
//				},
//				"major": "화학공학과",
//				"stuRequest" : {
//					"isVideo" : true,
//					"consultType" : 1,   // 0이면 현실적으로, 1이면 좋게
//					"query1" : "튜터한테 궁금한 것",
//					"query2" : "학과에 대해 궁금한 것",
//					"etc": "자유롭게 추가적인 질문"
//				},
//
//    }
//}
}
