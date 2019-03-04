package com.loki.sass.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysOperatLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysOperatLogExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserMobileIsNull() {
            addCriterion("user_mobile is null");
            return (Criteria) this;
        }

        public Criteria andUserMobileIsNotNull() {
            addCriterion("user_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andUserMobileEqualTo(String value) {
            addCriterion("user_mobile =", value, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileNotEqualTo(String value) {
            addCriterion("user_mobile <>", value, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileGreaterThan(String value) {
            addCriterion("user_mobile >", value, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileGreaterThanOrEqualTo(String value) {
            addCriterion("user_mobile >=", value, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileLessThan(String value) {
            addCriterion("user_mobile <", value, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileLessThanOrEqualTo(String value) {
            addCriterion("user_mobile <=", value, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileLike(String value) {
            addCriterion("user_mobile like", value, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileNotLike(String value) {
            addCriterion("user_mobile not like", value, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileIn(List<String> values) {
            addCriterion("user_mobile in", values, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileNotIn(List<String> values) {
            addCriterion("user_mobile not in", values, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileBetween(String value1, String value2) {
            addCriterion("user_mobile between", value1, value2, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileNotBetween(String value1, String value2) {
            addCriterion("user_mobile not between", value1, value2, "userMobile");
            return (Criteria) this;
        }

        public Criteria andIpAddrIsNull() {
            addCriterion("ip_addr is null");
            return (Criteria) this;
        }

        public Criteria andIpAddrIsNotNull() {
            addCriterion("ip_addr is not null");
            return (Criteria) this;
        }

        public Criteria andIpAddrEqualTo(String value) {
            addCriterion("ip_addr =", value, "ipAddr");
            return (Criteria) this;
        }

        public Criteria andIpAddrNotEqualTo(String value) {
            addCriterion("ip_addr <>", value, "ipAddr");
            return (Criteria) this;
        }

        public Criteria andIpAddrGreaterThan(String value) {
            addCriterion("ip_addr >", value, "ipAddr");
            return (Criteria) this;
        }

        public Criteria andIpAddrGreaterThanOrEqualTo(String value) {
            addCriterion("ip_addr >=", value, "ipAddr");
            return (Criteria) this;
        }

        public Criteria andIpAddrLessThan(String value) {
            addCriterion("ip_addr <", value, "ipAddr");
            return (Criteria) this;
        }

        public Criteria andIpAddrLessThanOrEqualTo(String value) {
            addCriterion("ip_addr <=", value, "ipAddr");
            return (Criteria) this;
        }

        public Criteria andIpAddrLike(String value) {
            addCriterion("ip_addr like", value, "ipAddr");
            return (Criteria) this;
        }

        public Criteria andIpAddrNotLike(String value) {
            addCriterion("ip_addr not like", value, "ipAddr");
            return (Criteria) this;
        }

        public Criteria andIpAddrIn(List<String> values) {
            addCriterion("ip_addr in", values, "ipAddr");
            return (Criteria) this;
        }

        public Criteria andIpAddrNotIn(List<String> values) {
            addCriterion("ip_addr not in", values, "ipAddr");
            return (Criteria) this;
        }

        public Criteria andIpAddrBetween(String value1, String value2) {
            addCriterion("ip_addr between", value1, value2, "ipAddr");
            return (Criteria) this;
        }

        public Criteria andIpAddrNotBetween(String value1, String value2) {
            addCriterion("ip_addr not between", value1, value2, "ipAddr");
            return (Criteria) this;
        }

        public Criteria andActionUrlIsNull() {
            addCriterion("action_url is null");
            return (Criteria) this;
        }

        public Criteria andActionUrlIsNotNull() {
            addCriterion("action_url is not null");
            return (Criteria) this;
        }

        public Criteria andActionUrlEqualTo(String value) {
            addCriterion("action_url =", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlNotEqualTo(String value) {
            addCriterion("action_url <>", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlGreaterThan(String value) {
            addCriterion("action_url >", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlGreaterThanOrEqualTo(String value) {
            addCriterion("action_url >=", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlLessThan(String value) {
            addCriterion("action_url <", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlLessThanOrEqualTo(String value) {
            addCriterion("action_url <=", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlLike(String value) {
            addCriterion("action_url like", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlNotLike(String value) {
            addCriterion("action_url not like", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlIn(List<String> values) {
            addCriterion("action_url in", values, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlNotIn(List<String> values) {
            addCriterion("action_url not in", values, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlBetween(String value1, String value2) {
            addCriterion("action_url between", value1, value2, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlNotBetween(String value1, String value2) {
            addCriterion("action_url not between", value1, value2, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andSubModuleIsNull() {
            addCriterion("sub_module is null");
            return (Criteria) this;
        }

        public Criteria andSubModuleIsNotNull() {
            addCriterion("sub_module is not null");
            return (Criteria) this;
        }

        public Criteria andSubModuleEqualTo(String value) {
            addCriterion("sub_module =", value, "subModule");
            return (Criteria) this;
        }

        public Criteria andSubModuleNotEqualTo(String value) {
            addCriterion("sub_module <>", value, "subModule");
            return (Criteria) this;
        }

        public Criteria andSubModuleGreaterThan(String value) {
            addCriterion("sub_module >", value, "subModule");
            return (Criteria) this;
        }

        public Criteria andSubModuleGreaterThanOrEqualTo(String value) {
            addCriterion("sub_module >=", value, "subModule");
            return (Criteria) this;
        }

        public Criteria andSubModuleLessThan(String value) {
            addCriterion("sub_module <", value, "subModule");
            return (Criteria) this;
        }

        public Criteria andSubModuleLessThanOrEqualTo(String value) {
            addCriterion("sub_module <=", value, "subModule");
            return (Criteria) this;
        }

        public Criteria andSubModuleLike(String value) {
            addCriterion("sub_module like", value, "subModule");
            return (Criteria) this;
        }

        public Criteria andSubModuleNotLike(String value) {
            addCriterion("sub_module not like", value, "subModule");
            return (Criteria) this;
        }

        public Criteria andSubModuleIn(List<String> values) {
            addCriterion("sub_module in", values, "subModule");
            return (Criteria) this;
        }

        public Criteria andSubModuleNotIn(List<String> values) {
            addCriterion("sub_module not in", values, "subModule");
            return (Criteria) this;
        }

        public Criteria andSubModuleBetween(String value1, String value2) {
            addCriterion("sub_module between", value1, value2, "subModule");
            return (Criteria) this;
        }

        public Criteria andSubModuleNotBetween(String value1, String value2) {
            addCriterion("sub_module not between", value1, value2, "subModule");
            return (Criteria) this;
        }

        public Criteria andModuleIsNull() {
            addCriterion("module is null");
            return (Criteria) this;
        }

        public Criteria andModuleIsNotNull() {
            addCriterion("module is not null");
            return (Criteria) this;
        }

        public Criteria andModuleEqualTo(String value) {
            addCriterion("module =", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleNotEqualTo(String value) {
            addCriterion("module <>", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleGreaterThan(String value) {
            addCriterion("module >", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleGreaterThanOrEqualTo(String value) {
            addCriterion("module >=", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleLessThan(String value) {
            addCriterion("module <", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleLessThanOrEqualTo(String value) {
            addCriterion("module <=", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleLike(String value) {
            addCriterion("module like", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleNotLike(String value) {
            addCriterion("module not like", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleIn(List<String> values) {
            addCriterion("module in", values, "module");
            return (Criteria) this;
        }

        public Criteria andModuleNotIn(List<String> values) {
            addCriterion("module not in", values, "module");
            return (Criteria) this;
        }

        public Criteria andModuleBetween(String value1, String value2) {
            addCriterion("module between", value1, value2, "module");
            return (Criteria) this;
        }

        public Criteria andModuleNotBetween(String value1, String value2) {
            addCriterion("module not between", value1, value2, "module");
            return (Criteria) this;
        }

        public Criteria andMeanIsNull() {
            addCriterion("mean is null");
            return (Criteria) this;
        }

        public Criteria andMeanIsNotNull() {
            addCriterion("mean is not null");
            return (Criteria) this;
        }

        public Criteria andMeanEqualTo(String value) {
            addCriterion("mean =", value, "mean");
            return (Criteria) this;
        }

        public Criteria andMeanNotEqualTo(String value) {
            addCriterion("mean <>", value, "mean");
            return (Criteria) this;
        }

        public Criteria andMeanGreaterThan(String value) {
            addCriterion("mean >", value, "mean");
            return (Criteria) this;
        }

        public Criteria andMeanGreaterThanOrEqualTo(String value) {
            addCriterion("mean >=", value, "mean");
            return (Criteria) this;
        }

        public Criteria andMeanLessThan(String value) {
            addCriterion("mean <", value, "mean");
            return (Criteria) this;
        }

        public Criteria andMeanLessThanOrEqualTo(String value) {
            addCriterion("mean <=", value, "mean");
            return (Criteria) this;
        }

        public Criteria andMeanLike(String value) {
            addCriterion("mean like", value, "mean");
            return (Criteria) this;
        }

        public Criteria andMeanNotLike(String value) {
            addCriterion("mean not like", value, "mean");
            return (Criteria) this;
        }

        public Criteria andMeanIn(List<String> values) {
            addCriterion("mean in", values, "mean");
            return (Criteria) this;
        }

        public Criteria andMeanNotIn(List<String> values) {
            addCriterion("mean not in", values, "mean");
            return (Criteria) this;
        }

        public Criteria andMeanBetween(String value1, String value2) {
            addCriterion("mean between", value1, value2, "mean");
            return (Criteria) this;
        }

        public Criteria andMeanNotBetween(String value1, String value2) {
            addCriterion("mean not between", value1, value2, "mean");
            return (Criteria) this;
        }

        public Criteria andFunctionIsNull() {
            addCriterion("function is null");
            return (Criteria) this;
        }

        public Criteria andFunctionIsNotNull() {
            addCriterion("function is not null");
            return (Criteria) this;
        }

        public Criteria andFunctionEqualTo(String value) {
            addCriterion("function =", value, "function");
            return (Criteria) this;
        }

        public Criteria andFunctionNotEqualTo(String value) {
            addCriterion("function <>", value, "function");
            return (Criteria) this;
        }

        public Criteria andFunctionGreaterThan(String value) {
            addCriterion("function >", value, "function");
            return (Criteria) this;
        }

        public Criteria andFunctionGreaterThanOrEqualTo(String value) {
            addCriterion("function >=", value, "function");
            return (Criteria) this;
        }

        public Criteria andFunctionLessThan(String value) {
            addCriterion("function <", value, "function");
            return (Criteria) this;
        }

        public Criteria andFunctionLessThanOrEqualTo(String value) {
            addCriterion("function <=", value, "function");
            return (Criteria) this;
        }

        public Criteria andFunctionLike(String value) {
            addCriterion("function like", value, "function");
            return (Criteria) this;
        }

        public Criteria andFunctionNotLike(String value) {
            addCriterion("function not like", value, "function");
            return (Criteria) this;
        }

        public Criteria andFunctionIn(List<String> values) {
            addCriterion("function in", values, "function");
            return (Criteria) this;
        }

        public Criteria andFunctionNotIn(List<String> values) {
            addCriterion("function not in", values, "function");
            return (Criteria) this;
        }

        public Criteria andFunctionBetween(String value1, String value2) {
            addCriterion("function between", value1, value2, "function");
            return (Criteria) this;
        }

        public Criteria andFunctionNotBetween(String value1, String value2) {
            addCriterion("function not between", value1, value2, "function");
            return (Criteria) this;
        }

        public Criteria andSubFunctionIsNull() {
            addCriterion("sub_function is null");
            return (Criteria) this;
        }

        public Criteria andSubFunctionIsNotNull() {
            addCriterion("sub_function is not null");
            return (Criteria) this;
        }

        public Criteria andSubFunctionEqualTo(String value) {
            addCriterion("sub_function =", value, "subFunction");
            return (Criteria) this;
        }

        public Criteria andSubFunctionNotEqualTo(String value) {
            addCriterion("sub_function <>", value, "subFunction");
            return (Criteria) this;
        }

        public Criteria andSubFunctionGreaterThan(String value) {
            addCriterion("sub_function >", value, "subFunction");
            return (Criteria) this;
        }

        public Criteria andSubFunctionGreaterThanOrEqualTo(String value) {
            addCriterion("sub_function >=", value, "subFunction");
            return (Criteria) this;
        }

        public Criteria andSubFunctionLessThan(String value) {
            addCriterion("sub_function <", value, "subFunction");
            return (Criteria) this;
        }

        public Criteria andSubFunctionLessThanOrEqualTo(String value) {
            addCriterion("sub_function <=", value, "subFunction");
            return (Criteria) this;
        }

        public Criteria andSubFunctionLike(String value) {
            addCriterion("sub_function like", value, "subFunction");
            return (Criteria) this;
        }

        public Criteria andSubFunctionNotLike(String value) {
            addCriterion("sub_function not like", value, "subFunction");
            return (Criteria) this;
        }

        public Criteria andSubFunctionIn(List<String> values) {
            addCriterion("sub_function in", values, "subFunction");
            return (Criteria) this;
        }

        public Criteria andSubFunctionNotIn(List<String> values) {
            addCriterion("sub_function not in", values, "subFunction");
            return (Criteria) this;
        }

        public Criteria andSubFunctionBetween(String value1, String value2) {
            addCriterion("sub_function between", value1, value2, "subFunction");
            return (Criteria) this;
        }

        public Criteria andSubFunctionNotBetween(String value1, String value2) {
            addCriterion("sub_function not between", value1, value2, "subFunction");
            return (Criteria) this;
        }

        public Criteria andParamDataIsNull() {
            addCriterion("param_data is null");
            return (Criteria) this;
        }

        public Criteria andParamDataIsNotNull() {
            addCriterion("param_data is not null");
            return (Criteria) this;
        }

        public Criteria andParamDataEqualTo(String value) {
            addCriterion("param_data =", value, "paramData");
            return (Criteria) this;
        }

        public Criteria andParamDataNotEqualTo(String value) {
            addCriterion("param_data <>", value, "paramData");
            return (Criteria) this;
        }

        public Criteria andParamDataGreaterThan(String value) {
            addCriterion("param_data >", value, "paramData");
            return (Criteria) this;
        }

        public Criteria andParamDataGreaterThanOrEqualTo(String value) {
            addCriterion("param_data >=", value, "paramData");
            return (Criteria) this;
        }

        public Criteria andParamDataLessThan(String value) {
            addCriterion("param_data <", value, "paramData");
            return (Criteria) this;
        }

        public Criteria andParamDataLessThanOrEqualTo(String value) {
            addCriterion("param_data <=", value, "paramData");
            return (Criteria) this;
        }

        public Criteria andParamDataLike(String value) {
            addCriterion("param_data like", value, "paramData");
            return (Criteria) this;
        }

        public Criteria andParamDataNotLike(String value) {
            addCriterion("param_data not like", value, "paramData");
            return (Criteria) this;
        }

        public Criteria andParamDataIn(List<String> values) {
            addCriterion("param_data in", values, "paramData");
            return (Criteria) this;
        }

        public Criteria andParamDataNotIn(List<String> values) {
            addCriterion("param_data not in", values, "paramData");
            return (Criteria) this;
        }

        public Criteria andParamDataBetween(String value1, String value2) {
            addCriterion("param_data between", value1, value2, "paramData");
            return (Criteria) this;
        }

        public Criteria andParamDataNotBetween(String value1, String value2) {
            addCriterion("param_data not between", value1, value2, "paramData");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("memo is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("memo is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("memo =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("memo <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("memo >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("memo >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("memo <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("memo <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("memo like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("memo not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("memo in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("memo not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("memo between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("memo not between", value1, value2, "memo");
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