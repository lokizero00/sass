package com.loki.sass.domain.mapper;

import com.loki.sass.domain.model.AccountJournal;
import com.loki.sass.domain.model.AccountJournalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountJournalMapper {
    long countByExample(AccountJournalExample example);

    int deleteByExample(AccountJournalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountJournal record);

    int insertSelective(AccountJournal record);

    List<AccountJournal> selectByExample(AccountJournalExample example);

    AccountJournal selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountJournal record, @Param("example") AccountJournalExample example);

    int updateByExample(@Param("record") AccountJournal record, @Param("example") AccountJournalExample example);

    int updateByPrimaryKeySelective(AccountJournal record);

    int updateByPrimaryKey(AccountJournal record);
}