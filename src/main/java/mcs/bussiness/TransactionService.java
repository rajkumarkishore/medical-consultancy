package mcs.bussiness;

import java.util.List;

import mcs.model.Appointment;
import mcs.vo.TransactionsVO;

public interface TransactionService {

	List<Appointment> findTransactions(TransactionsVO vo);

}
