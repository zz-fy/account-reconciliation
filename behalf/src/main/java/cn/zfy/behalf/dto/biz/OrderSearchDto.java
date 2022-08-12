package cn.zfy.behalf.dto.biz;

import cn.zfy.behalf.dto.base.PageRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderSearchDto extends PageRequest {

    private Long orderId;

    private String orderStartTime;

    private String orderEndTime;

    private Long productId;

    private String customerNickname;

    private String customerRealName;

    private String customerPhone;

    private Integer status;

    private Long soldAccountId;

    private Long soldPlatformId;

    private Long issuingPlatformId;

    private Long issuingCompanyId;


}
