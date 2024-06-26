package com.joyfulresort.reserveorder.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import com.joyfulresort.member.model.MemberVO;
import com.joyfulresort.reservesession.model.RessionVO;

@Entity

@Table(name = "reserve_order")
public class ResVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reserve_order_id")
	private Integer reserveOrderId;

	@NotNull(message = "日期請勿空白")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "reserve_order_date")
	private LocalDate reserveOrderDate =LocalDate.now() ;
	

	
	
	@NotNull(message = "人數請勿空白")
	@Column(name = "reserve_number")
	private Integer reserveNumber;

	
	@NotNull
	@Column(name = "reserve_order_state")
	private Byte reserveOrderState = 1;

	@NotNull(message = "預定日期請勿空白")
	@Column(name = "booking_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime bookingDate;

	@Column(name = "order_note", length = 60)
	private String orderNote;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "member_id")
	private MemberVO memberVO;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reserve_session_id")
	private RessionVO ressionVO;
	
	
	public Integer getReserveOrderId() {
		return reserveOrderId;
	}

	public void setReserveOrderId(Integer reserveOrderId) {
		this.reserveOrderId = reserveOrderId;
	}

	public LocalDate getReserveOrderDate() {
		return reserveOrderDate;
	}
		
	public void setReserveOrderDate(LocalDate reserveOrderDate) {
		this.reserveOrderDate = reserveOrderDate;
	}

	public Integer getReserveNumber() {
		return reserveNumber;
	}

	public void setReserveNumber(Integer reserveNumber) {
		this.reserveNumber = reserveNumber;
	}

	public Byte getReserveOrderState() {
		return reserveOrderState;
	}

	public void setReserveOrderState(Byte reserveOrderState) {
		this.reserveOrderState = reserveOrderState;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getOrderNote() {
		return orderNote;
	}

	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public RessionVO getRessionVO() {
		return ressionVO;
	}

	public void setRessionVO(RessionVO ressionVO) {
		this.ressionVO = ressionVO;
	}

	public ResVO() {
		super();

	}

	@Override
	public String toString() {
		return "ResVO [reserveOrderId=" + reserveOrderId + ", reserveOrderDate=" + reserveOrderDate + ", reserveNumber="
				+ reserveNumber + ", reserveOrderState=" + reserveOrderState + ", bookingDate=" + bookingDate
				+ ", orderNote=" + orderNote + ", memberVO=" + memberVO + ", ressionVO=" + ressionVO + "]";
	}

}
