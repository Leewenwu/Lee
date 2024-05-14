package com.joyfulresort.member.model;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyfulresort.reserveorder.model.ResVO;

@Service
public class MemberService {

	@Autowired
	MemberRepository repository;

	public void addmember(MemberVO memberVO) {
		repository.save(memberVO);
	}

	public void updatemember(MemberVO memberVO) {
		repository.save(memberVO);
	}

	public void deletemember(Integer member) {
		if (repository.existsById(member))
			repository.deleteById(member);

	}

	public MemberVO getOneMember(Integer member) {
		Optional<MemberVO> optional = repository.findById(member);
		return optional.orElse(null);

	}

	public List<MemberVO> getAllmember() {
		return repository.findAll();
	}
	
	public Set<ResVO> getResByMember(Integer member){
		return getOneMember(member).getReserveOrders();
	}
}
