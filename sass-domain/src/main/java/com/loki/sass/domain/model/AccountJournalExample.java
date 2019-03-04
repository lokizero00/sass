package com.loki.sass.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountJournalExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AccountJournalExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNull() {
            addCriterion("account_id is null");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNotNull() {
            addCriterion("account_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccountIdEqualTo(Integer value) {
            addCriterion("account_id =", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotEqualTo(Integer value) {
            addCriterion("account_id <>", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThan(Integer value) {
            addCriterion("account_id >", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("account_id >=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThan(Integer value) {
            addCriterion("account_id <", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThanOrEqualTo(Integer value) {
            addCriterion("account_id <=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdIn(List<Integer> values) {
            addCriterion("account_id in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotIn(List<Integer> values) {
            addCriterion("account_id not in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdBetween(Integer value1, Integer value2) {
            addCriterion("account_id between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotBetween(Integer value1, Integer value2) {
            addCriterion("account_id not between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andInnerBusiNoIsNull() {
            addCriterion("inner_busi_no is null");
            return (Criteria) this;
        }

        public Criteria andInnerBusiNoIsNotNull() {
            addCriterion("inner_busi_no is not null");
            return (Criteria) this;
        }

        public Criteria andInnerBusiNoEqualTo(String value) {
            addCriterion("inner_busi_no =", value, "innerBusiNo");
            return (Criteria) this;
        }

        public Criteria andInnerBusiNoNotEqualTo(String value) {
            addCriterion("inner_busi_no <>", value, "innerBusiNo");
            return (Criteria) this;
        }

        public Criteria andInnerBusiNoGreaterThan(String value) {
            addCriterion("inner_busi_no >", value, "innerBusiNo");
            return (Criteria) this;
        }

        public Criteria andInnerBusiNoGreaterThanOrEqualTo(String value) {
            addCriterion("inner_busi_no >=", value, "innerBusiNo");
            return (Criteria) this;
        }

        public Criteria andInnerBusiNoLessThan(String value) {
            addCriterion("inner_busi_no <", value, "innerBusiNo");
            return (Criteria) this;
        }

        public Criteria andInnerBusiNoLessThanOrEqualTo(String value) {
            addCriterion("inner_busi_no <=", value, "innerBusiNo");
            return (Criteria) this;
        }

        public Criteria andInnerBusiNoLike(String value) {
            addCriterion("inner_busi_no like", value, "innerBusiNo");
            return (Criteria) this;
        }

        public Criteria andInnerBusiNoNotLike(String value) {
            addCriterion("inner_busi_no not like", value, "innerBusiNo");
            return (Criteria) this;
        }

        public Criteria andInnerBusiNoIn(List<String> values) {
            addCriterion("inner_busi_no in", values, "innerBusiNo");
            return (Criteria) this;
        }

        public Criteria andInnerBusiNoNotIn(List<String> values) {
            addCriterion("inner_busi_no not in", values, "innerBusiNo");
            return (Criteria) this;
        }

        public Criteria andInnerBusiNoBetween(String value1, String value2) {
            addCriterion("inner_busi_no between", value1, value2, "innerBusiNo");
            return (Criteria) this;
        }

        public Criteria andInnerBusiNoNotBetween(String value1, String value2) {
            addCriterion("inner_busi_no not between", value1, value2, "innerBusiNo");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(BigDecimal value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(BigDecimal value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(BigDecimal value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(BigDecimal value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<BigDecimal> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<BigDecimal> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andOpTimeIsNull() {
            addCriterion("op_time is null");
            return (Criteria) this;
        }

        public Criteria andOpTimeIsNotNull() {
            addCriterion("op_time is not null");
            return (Criteria) this;
        }

        public Criteria andOpTimeEqualTo(Date value) {
            addCriterion("op_time =", value, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeNotEqualTo(Date value) {
            addCriterion("op_time <>", value, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeGreaterThan(Date value) {
            addCriterion("op_time >", value, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("op_time >=", value, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeLessThan(Date value) {
            addCriterion("op_time <", value, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeLessThanOrEqualTo(Date value) {
            addCriterion("op_time <=", value, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeIn(List<Date> values) {
            addCriterion("op_time in", values, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeNotIn(List<Date> values) {
            addCriterion("op_time not in", values, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeBetween(Date value1, Date value2) {
            addCriterion("op_time between", value1, value2, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeNotBetween(Date value1, Date value2) {
            addCriterion("op_time not between", value1, value2, "opTime");
            return (Criteria) this;
        }

        public Criteria andThirdChannelIsNull() {
            addCriterion("third_channel is null");
            return (Criteria) this;
        }

        public Criteria andThirdChannelIsNotNull() {
            addCriterion("third_channel is not null");
            return (Criteria) this;
        }

        public Criteria andThirdChannelEqualTo(Integer value) {
            addCriterion("third_channel =", value, "thirdChannel");
            return (Criteria) this;
        }

        public Criteria andThirdChannelNotEqualTo(Integer value) {
            addCriterion("third_channel <>", value, "thirdChannel");
            return (Criteria) this;
        }

        public Criteria andThirdChannelGreaterThan(Integer value) {
            addCriterion("third_channel >", value, "thirdChannel");
            return (Criteria) this;
        }

        public Criteria andThirdChannelGreaterThanOrEqualTo(Integer value) {
            addCriterion("third_channel >=", value, "thirdChannel");
            return (Criteria) this;
        }

        public Criteria andThirdChannelLessThan(Integer value) {
            addCriterion("third_channel <", value, "thirdChannel");
            return (Criteria) this;
        }

        public Criteria andThirdChannelLessThanOrEqualTo(Integer value) {
            addCriterion("third_channel <=", value, "thirdChannel");
            return (Criteria) this;
        }

        public Criteria andThirdChannelIn(List<Integer> values) {
            addCriterion("third_channel in", values, "thirdChannel");
            return (Criteria) this;
        }

        public Criteria andThirdChannelNotIn(List<Integer> values) {
            addCriterion("third_channel not in", values, "thirdChannel");
            return (Criteria) this;
        }

        public Criteria andThirdChannelBetween(Integer value1, Integer value2) {
            addCriterion("third_channel between", value1, value2, "thirdChannel");
            return (Criteria) this;
        }

        public Criteria andThirdChannelNotBetween(Integer value1, Integer value2) {
            addCriterion("third_channel not between", value1, value2, "thirdChannel");
            return (Criteria) this;
        }

        public Criteria andThirdTransNoIsNull() {
            addCriterion("third_trans_no is null");
            return (Criteria) this;
        }

        public Criteria andThirdTransNoIsNotNull() {
            addCriterion("third_trans_no is not null");
            return (Criteria) this;
        }

        public Criteria andThirdTransNoEqualTo(String value) {
            addCriterion("third_trans_no =", value, "thirdTransNo");
            return (Criteria) this;
        }

        public Criteria andThirdTransNoNotEqualTo(String value) {
            addCriterion("third_trans_no <>", value, "thirdTransNo");
            return (Criteria) this;
        }

        public Criteria andThirdTransNoGreaterThan(String value) {
            addCriterion("third_trans_no >", value, "thirdTransNo");
            return (Criteria) this;
        }

        public Criteria andThirdTransNoGreaterThanOrEqualTo(String value) {
            addCriterion("third_trans_no >=", value, "thirdTransNo");
            return (Criteria) this;
        }

        public Criteria andThirdTransNoLessThan(String value) {
            addCriterion("third_trans_no <", value, "thirdTransNo");
            return (Criteria) this;
        }

        public Criteria andThirdTransNoLessThanOrEqualTo(String value) {
            addCriterion("third_trans_no <=", value, "thirdTransNo");
            return (Criteria) this;
        }

        public Criteria andThirdTransNoLike(String value) {
            addCriterion("third_trans_no like", value, "thirdTransNo");
            return (Criteria) this;
        }

        public Criteria andThirdTransNoNotLike(String value) {
            addCriterion("third_trans_no not like", value, "thirdTransNo");
            return (Criteria) this;
        }

        public Criteria andThirdTransNoIn(List<String> values) {
            addCriterion("third_trans_no in", values, "thirdTransNo");
            return (Criteria) this;
        }

        public Criteria andThirdTransNoNotIn(List<String> values) {
            addCriterion("third_trans_no not in", values, "thirdTransNo");
            return (Criteria) this;
        }

        public Criteria andThirdTransNoBetween(String value1, String value2) {
            addCriterion("third_trans_no between", value1, value2, "thirdTransNo");
            return (Criteria) this;
        }

        public Criteria andThirdTransNoNotBetween(String value1, String value2) {
            addCriterion("third_trans_no not between", value1, value2, "thirdTransNo");
            return (Criteria) this;
        }

        public Criteria andThirdReceiptTimeIsNull() {
            addCriterion("third_receipt_time is null");
            return (Criteria) this;
        }

        public Criteria andThirdReceiptTimeIsNotNull() {
            addCriterion("third_receipt_time is not null");
            return (Criteria) this;
        }

        public Criteria andThirdReceiptTimeEqualTo(Date value) {
            addCriterion("third_receipt_time =", value, "thirdReceiptTime");
            return (Criteria) this;
        }

        public Criteria andThirdReceiptTimeNotEqualTo(Date value) {
            addCriterion("third_receipt_time <>", value, "thirdReceiptTime");
            return (Criteria) this;
        }

        public Criteria andThirdReceiptTimeGreaterThan(Date value) {
            addCriterion("third_receipt_time >", value, "thirdReceiptTime");
            return (Criteria) this;
        }

        public Criteria andThirdReceiptTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("third_receipt_time >=", value, "thirdReceiptTime");
            return (Criteria) this;
        }

        public Criteria andThirdReceiptTimeLessThan(Date value) {
            addCriterion("third_receipt_time <", value, "thirdReceiptTime");
            return (Criteria) this;
        }

        public Criteria andThirdReceiptTimeLessThanOrEqualTo(Date value) {
            addCriterion("third_receipt_time <=", value, "thirdReceiptTime");
            return (Criteria) this;
        }

        public Criteria andThirdReceiptTimeIn(List<Date> values) {
            addCriterion("third_receipt_time in", values, "thirdReceiptTime");
            return (Criteria) this;
        }

        public Criteria andThirdReceiptTimeNotIn(List<Date> values) {
            addCriterion("third_receipt_time not in", values, "thirdReceiptTime");
            return (Criteria) this;
        }

        public Criteria andThirdReceiptTimeBetween(Date value1, Date value2) {
            addCriterion("third_receipt_time between", value1, value2, "thirdReceiptTime");
            return (Criteria) this;
        }

        public Criteria andThirdReceiptTimeNotBetween(Date value1, Date value2) {
            addCriterion("third_receipt_time not between", value1, value2, "thirdReceiptTime");
            return (Criteria) this;
        }

        public Criteria andThirdReceiptAmountIsNull() {
            addCriterion("third_receipt_amount is null");
            return (Criteria) this;
        }

        public Criteria andThirdReceiptAmountIsNotNull() {
            addCriterion("third_receipt_amount is not null");
            return (Criteria) this;
        }

        public Criteria andThirdReceiptAmountEqualTo(BigDecimal value) {
            addCriterion("third_receipt_amount =", value, "thirdReceiptAmount");
            return (Criteria) this;
        }

        public Criteria andThirdReceiptAmountNotEqualTo(BigDecimal value) {
            addCriterion("third_receipt_amount <>", value, "thirdReceiptAmount");
            return (Criteria) this;
        }

        public Criteria andThirdReceiptAmountGreaterThan(BigDecimal value) {
            addCriterion("third_receipt_amount >", value, "thirdReceiptAmount");
            return (Criteria) this;
        }

        public Criteria andThirdReceiptAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("third_receipt_amount >=", value, "thirdReceiptAmount");
            return (Criteria) this;
        }

        public Criteria andThirdReceiptAmountLessThan(BigDecimal value) {
            addCriterion("third_receipt_amount <", value, "thirdReceiptAmount");
            return (Criteria) this;
        }

        public Criteria andThirdReceiptAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("third_receipt_amount <=", value, "thirdReceiptAmount");
            return (Criteria) this;
        }

        public Criteria andThirdReceiptAmountIn(List<BigDecimal> values) {
            addCriterion("third_receipt_amount in", values, "thirdReceiptAmount");
            return (Criteria) this;
        }

        public Criteria andThirdReceiptAmountNotIn(List<BigDecimal> values) {
            addCriterion("third_receipt_amount not in", values, "thirdReceiptAmount");
            return (Criteria) this;
        }

        public Criteria andThirdReceiptAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("third_receipt_amount between", value1, value2, "thirdReceiptAmount");
            return (Criteria) this;
        }

        public Criteria andThirdReceiptAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("third_receipt_amount not between", value1, value2, "thirdReceiptAmount");
            return (Criteria) this;
        }

        public Criteria andNeedThirdConfirmIsNull() {
            addCriterion("need_third_confirm is null");
            return (Criteria) this;
        }

        public Criteria andNeedThirdConfirmIsNotNull() {
            addCriterion("need_third_confirm is not null");
            return (Criteria) this;
        }

        public Criteria andNeedThirdConfirmEqualTo(Integer value) {
            addCriterion("need_third_confirm =", value, "needThirdConfirm");
            return (Criteria) this;
        }

        public Criteria andNeedThirdConfirmNotEqualTo(Integer value) {
            addCriterion("need_third_confirm <>", value, "needThirdConfirm");
            return (Criteria) this;
        }

        public Criteria andNeedThirdConfirmGreaterThan(Integer value) {
            addCriterion("need_third_confirm >", value, "needThirdConfirm");
            return (Criteria) this;
        }

        public Criteria andNeedThirdConfirmGreaterThanOrEqualTo(Integer value) {
            addCriterion("need_third_confirm >=", value, "needThirdConfirm");
            return (Criteria) this;
        }

        public Criteria andNeedThirdConfirmLessThan(Integer value) {
            addCriterion("need_third_confirm <", value, "needThirdConfirm");
            return (Criteria) this;
        }

        public Criteria andNeedThirdConfirmLessThanOrEqualTo(Integer value) {
            addCriterion("need_third_confirm <=", value, "needThirdConfirm");
            return (Criteria) this;
        }

        public Criteria andNeedThirdConfirmIn(List<Integer> values) {
            addCriterion("need_third_confirm in", values, "needThirdConfirm");
            return (Criteria) this;
        }

        public Criteria andNeedThirdConfirmNotIn(List<Integer> values) {
            addCriterion("need_third_confirm not in", values, "needThirdConfirm");
            return (Criteria) this;
        }

        public Criteria andNeedThirdConfirmBetween(Integer value1, Integer value2) {
            addCriterion("need_third_confirm between", value1, value2, "needThirdConfirm");
            return (Criteria) this;
        }

        public Criteria andNeedThirdConfirmNotBetween(Integer value1, Integer value2) {
            addCriterion("need_third_confirm not between", value1, value2, "needThirdConfirm");
            return (Criteria) this;
        }

        public Criteria andResponseContentIsNull() {
            addCriterion("response_content is null");
            return (Criteria) this;
        }

        public Criteria andResponseContentIsNotNull() {
            addCriterion("response_content is not null");
            return (Criteria) this;
        }

        public Criteria andResponseContentEqualTo(String value) {
            addCriterion("response_content =", value, "responseContent");
            return (Criteria) this;
        }

        public Criteria andResponseContentNotEqualTo(String value) {
            addCriterion("response_content <>", value, "responseContent");
            return (Criteria) this;
        }

        public Criteria andResponseContentGreaterThan(String value) {
            addCriterion("response_content >", value, "responseContent");
            return (Criteria) this;
        }

        public Criteria andResponseContentGreaterThanOrEqualTo(String value) {
            addCriterion("response_content >=", value, "responseContent");
            return (Criteria) this;
        }

        public Criteria andResponseContentLessThan(String value) {
            addCriterion("response_content <", value, "responseContent");
            return (Criteria) this;
        }

        public Criteria andResponseContentLessThanOrEqualTo(String value) {
            addCriterion("response_content <=", value, "responseContent");
            return (Criteria) this;
        }

        public Criteria andResponseContentLike(String value) {
            addCriterion("response_content like", value, "responseContent");
            return (Criteria) this;
        }

        public Criteria andResponseContentNotLike(String value) {
            addCriterion("response_content not like", value, "responseContent");
            return (Criteria) this;
        }

        public Criteria andResponseContentIn(List<String> values) {
            addCriterion("response_content in", values, "responseContent");
            return (Criteria) this;
        }

        public Criteria andResponseContentNotIn(List<String> values) {
            addCriterion("response_content not in", values, "responseContent");
            return (Criteria) this;
        }

        public Criteria andResponseContentBetween(String value1, String value2) {
            addCriterion("response_content between", value1, value2, "responseContent");
            return (Criteria) this;
        }

        public Criteria andResponseContentNotBetween(String value1, String value2) {
            addCriterion("response_content not between", value1, value2, "responseContent");
            return (Criteria) this;
        }

        public Criteria andOutRequestNoIsNull() {
            addCriterion("out_request_no is null");
            return (Criteria) this;
        }

        public Criteria andOutRequestNoIsNotNull() {
            addCriterion("out_request_no is not null");
            return (Criteria) this;
        }

        public Criteria andOutRequestNoEqualTo(String value) {
            addCriterion("out_request_no =", value, "outRequestNo");
            return (Criteria) this;
        }

        public Criteria andOutRequestNoNotEqualTo(String value) {
            addCriterion("out_request_no <>", value, "outRequestNo");
            return (Criteria) this;
        }

        public Criteria andOutRequestNoGreaterThan(String value) {
            addCriterion("out_request_no >", value, "outRequestNo");
            return (Criteria) this;
        }

        public Criteria andOutRequestNoGreaterThanOrEqualTo(String value) {
            addCriterion("out_request_no >=", value, "outRequestNo");
            return (Criteria) this;
        }

        public Criteria andOutRequestNoLessThan(String value) {
            addCriterion("out_request_no <", value, "outRequestNo");
            return (Criteria) this;
        }

        public Criteria andOutRequestNoLessThanOrEqualTo(String value) {
            addCriterion("out_request_no <=", value, "outRequestNo");
            return (Criteria) this;
        }

        public Criteria andOutRequestNoLike(String value) {
            addCriterion("out_request_no like", value, "outRequestNo");
            return (Criteria) this;
        }

        public Criteria andOutRequestNoNotLike(String value) {
            addCriterion("out_request_no not like", value, "outRequestNo");
            return (Criteria) this;
        }

        public Criteria andOutRequestNoIn(List<String> values) {
            addCriterion("out_request_no in", values, "outRequestNo");
            return (Criteria) this;
        }

        public Criteria andOutRequestNoNotIn(List<String> values) {
            addCriterion("out_request_no not in", values, "outRequestNo");
            return (Criteria) this;
        }

        public Criteria andOutRequestNoBetween(String value1, String value2) {
            addCriterion("out_request_no between", value1, value2, "outRequestNo");
            return (Criteria) this;
        }

        public Criteria andOutRequestNoNotBetween(String value1, String value2) {
            addCriterion("out_request_no not between", value1, value2, "outRequestNo");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}