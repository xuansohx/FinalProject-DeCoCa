<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!-- Content page -->
<section class="bg0 p-t-104 p-b-116">
	<div class="container">
		<div class="p-b-10">
			<h3 class="ltext-103 cl5"></h3>
		</div>
		<div class="flex-w flex-tr">
			<div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
				<h4 class="mtext-105 cl2 txt-center p-b-30">ORDER DETAIL</h4>

				<div class="col-sm-6 p-b-5 m-lr-auto">
					<label class="stext-102 cl3" for="name">ORDER LIST</label>
					<table>
						<tr class="table_row">주문내역
						</tr>
					</table>
				</div>

				<div class="col-sm-6 p-b-5 m-lr-auto">
					<label class="stext-102 cl3 total" for="total">TOTAL</label> <input
						type="text" class="stext-102 cl3 total ttt" id="pro_no" name="ttt" />
					<input type="hidden" class="stext-102 cl3 total qtt" id="pro_no2"
						name="qtt" />

				</div>

				<div class="col-sm-6 p-b-5 m-lr-auto">
					<label class="stext-102 cl3" for="name">NAME</label> <input
						class="size-111 bor8 stext-102 cl2 p-lr-20" id="name" type="text"
						name="name">
				</div>

				<div class="col-sm-6 p-b-5 m-lr-auto">
					<label class="stext-102 cl3" for="phone">PHONE</label> <input
						class="size-111 bor8 stext-102 cl2 p-lr-20" id="phone"
						type="number" name="phone">
				</div>

				<div class="col-sm-6 p-b-5 m-lr-auto">
					<label class="stext-102 cl3" for="addr">ADDRESS</label> <input
						class="size-111 bor8 stext-102 cl2 p-lr-20" id="address"
						type="text" name="address">
				</div>

				<div class="col-sm-6 p-b-5 m-lr-auto">
					<label class="stext-102 cl3" for="payment">PAYMENT</label>
					<div class="size-204 respon6-next">
						<div class="rs1-select2 bor8 bg0">지불방식</div>
					</div>
				</div>

				<div class="col-sm-6 p-b-5 m-lr-auto">
					<input type="submit"
						class="flex-c-m stext-101 cl2 size-119 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-10"
						value="CONFIRM" style="width: 300px; height: 50px">
				</div>


			</div>
		</div>
	</div>
</section>