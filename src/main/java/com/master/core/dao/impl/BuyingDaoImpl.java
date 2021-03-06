package com.master.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.master.core.dao.BuyingDao;
import com.master.core.demain.Buying;
import com.master.core.orm.GeneralDAOImpl;
import com.master.core.orm.Page;


@Repository("BuyingDao")
@Transactional
@SuppressWarnings("unchecked") 
public class BuyingDaoImpl extends GeneralDAOImpl<Buying, Long> implements BuyingDao{

	public BuyingDaoImpl() {
		super(Buying.class);
	}

	@Override
	public void updateBuying(Buying buying) {
		Buying b = findById(buying.getId());
		b.setArriveDate(buying.getArriveDate());
		b.setCurrency(buying.getCurrency());
		b.setExchangeRate(buying.getExchangeRate());
		b.setName(buying.getName());
		b.setOrderNumber(buying.getOrderNumber());
		b.setPayer(buying.getPayer());
		b.setQuantity(buying.getQuantity());
		b.setRecordDate(buying.getRecordDate());
		b.setRemain(buying.getRemain());
		b.setStockpile(buying.isStockpile());
		b.setUnitPrice(buying.getUnitPrice());
		b.setComment(buying.getComment());
	}

	@Override
	public List<Buying> findAllStockpiles() {
		return find("from Buying where stockpile=? order by id desc", true);
	}

	@Override
	public List<Buying> findAllAdditions() {
		return find("from Buying where stockpile=? order by id desc", false);
	}

	@Override
	public List<Buying> findAllStockpilesWithRemain() {
		return find("from Buying where stockpile=? and remain>0 order by id desc", true);
	}


	@Override
	public Page<Buying> findAllStockpiles(Page<Buying> page) {
		return findPage(page, "from Buying where stockpile=? order by "+page.getOrderBy()+" desc",true);
	}


}
