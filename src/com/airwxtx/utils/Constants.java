package com.airwxtx.utils;

import java.util.ArrayList;
import java.util.List;

import com.airwxtx.authority.entity.Authority;
import com.airwxtx.authority.entity.AuthorityNumber;

public abstract class Constants {

	// 分页大小
	public static final int PAGE_SIZE = 10;

	// 冻结卡次数上限-2次
	public static final int FREEZE_CARD_UPPER_LIMIT = 2;
	
	// 删除记录时间上限-10分钟
	public static final int DELETE_RECORD_UPPER_LIMIT = 10 * 60 * 1000;
	
	// 默认密码“123456”
	public static final String PASSWORD = "e10adc3949ba59abbe56e057f20f883e";
	
	public static final List<Authority> ALL_AUTHORITIES = new ArrayList<>();
	
	static {
		ALL_AUTHORITIES.add(new Authority(AuthorityNumber.SAVE_CLIENT, "录入会员信息"));
		ALL_AUTHORITIES.add(new Authority(AuthorityNumber.UPDATE_CLIENT, "修改会员信息"));
		ALL_AUTHORITIES.add(new Authority(AuthorityNumber.EXPORT_CLIENT, "导出会员信息"));
		ALL_AUTHORITIES.add(new Authority(AuthorityNumber.SELL_CARD, "录入会员卡信息"));
		ALL_AUTHORITIES.add(new Authority(AuthorityNumber.CHARGE, "充值"));
		ALL_AUTHORITIES.add(new Authority(AuthorityNumber.PAY, "扣款"));
		ALL_AUTHORITIES.add(new Authority(AuthorityNumber.FREEZE_CARD_UNLIMITED, "冻结会员卡"));
		ALL_AUTHORITIES.add(new Authority(AuthorityNumber.FREEZE_CARD_LIMITED, "冻结会员卡每天2次"));
		ALL_AUTHORITIES.add(new Authority(AuthorityNumber.UNFREEZE_CARD, "解冻会员卡"));
		ALL_AUTHORITIES.add(new Authority(AuthorityNumber.DELETE_RECORD_UNLIMITED, "删除消费记录"));
		ALL_AUTHORITIES.add(new Authority(AuthorityNumber.DELETE_RECORD_LIMITED, "10分钟内删除消费记录"));
		ALL_AUTHORITIES.add(new Authority(AuthorityNumber.EXPORT_RECORD, "导出消费记录"));
		ALL_AUTHORITIES.add(new Authority(AuthorityNumber.CONTROL_USER, "管理系统用户"));
	}
	
}
