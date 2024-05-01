package com.joyfulresort.reserveorder.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.DynamicUpdate;

import com.joyfulresort.member.model.MemberVO;
import com.joyfulresort.reservesession.model.RessionVO;

@Entity
@DynamicUpdate
@Table(name = "reserve_order")
public class ResVO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotEmpty(message="ID請勿空白")
	@Column(name = "reserve_order_id")
	private Integer reserveOrderId;

	@NotEmpty(message="請勿空白")
	@Column(name = "reserve_order_date")
	@Temporal(TemporalType.DATE)
	private Date reserveOrderDate;

	@NotEmpty(message="人數請勿空白")
	@Column(name = "reserve_number")
	private Integer reserveNumber;

	
	@Column(name = "reserve_order_state")
	private Byte reserveOrderState = 1;
	
	@NotEmpty(message="預定日期請勿空白")
	@Column(name = "booking_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date bookingDate;

	@Column(name = "order_note", length = 50)
	private String orderNote;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "member_id", referencedColumnName = "member_id")
	private MemberVO memberVO;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reserve_session_id", referencedColumnName = "reserve_session_id")
	private RessionVO ressionVO;

	public Integer getReserveOrderId() {
		return reserveOrderId;
	}

	public void setReserveOrderId(Integer reserveOrderId) {
		this.reserveOrderId = reserveOrderId;
	}

	public Date getReserveOrderDate() {
		return reserveOrderDate;
	}

	public void setReserveOrderDate(Date reserveOrderDate) {
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

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
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

}
