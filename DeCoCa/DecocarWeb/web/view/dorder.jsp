<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Contact</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="images/icons/favicon.png" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/linearicons-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
</head>
<!--===============================================================================================-->
<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/select2/select2.min.js"></script>
<script>
	$(".js-select2").each(function() {
		$(this).select2({
			minimumResultsForSearch : 20,
			dropdownParent : $(this).next('.dropDownSelect2')
		});
	})
</script>
<!--===============================================================================================-->
<script src="vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
<script>
	$('.js-pscroll').each(function() {
		$(this).css('position', 'relative');
		$(this).css('overflow', 'hidden');
		var ps = new PerfectScrollbar(this, {
			wheelSpeed : 1,
			scrollingThreshold : 1000,
			wheelPropagation : false
		});

		$(window).on('resize', function() {
			ps.update();
		})
	});
</script>
<!--===============================================================================================-->
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAKFWBqlKAGCeS1rMVoaNlwyayu0e0YRes"></script>
<script src="js/map-custom.js"></script>
<!--===============================================================================================-->
<script src="js/main.js"></script>
<script>
	$(document).ready(function() {
		var a = $('.column-3').text();
		var b = $('.column-4').text();
		var result;
		result = a * b;
		$('.column-5').html(result);
	});
</script>

<body class="animsition">
	<!-- Content page -->
	<section class="bg0 p-t-104 p-b-116">
		<div class="container">
			<div class="p-b-10">
				<h3 class="ltext-103 cl5"></h3>
			</div>
			<div class="flex-w flex-tr">
				<div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
					<form action="dorderimpl.mc" method="POST">
						<h4 class="mtext-105 cl2 txt-center p-b-30">ORDER</h4>

						<div class="col-sm-6 p-b-5 m-lr-auto">
							<label class="stext-102 cl3" for="name">ORDER LIST</label>
							<div>
								<table>
									<tr class="table_row">
										<td class="column-1"><img class="how-itemcart1"
											src="images/${dorder.product_picture1 }" alt="IMG"></td>
										<td class="column-2" style="padding: 10px;">${dorder.product_no }</td>
										<td class="column-3" style="padding: 10px;">${dorder.product_price }</td>
										<td class="column-4" style="padding: 10px;">${diqt }</td>
										<td class="column-5" style="padding: 10px;"></td>
									</tr>
								</table>
							</div>
						</div>
						<div class="col-sm-6 p-b-5 m-lr-auto">
							<label class="stext-102 cl3" for="name">NAME</label> <input
								class="size-111 bor8 stext-102 cl2 p-lr-20" id="name"
								type="text" name="name">
						</div>

						<div class="col-sm-6 p-b-5 m-lr-auto">
							<label class="stext-102 cl3" for="name">PHONE</label> <input
								class="size-111 bor8 stext-102 cl2 p-lr-20" id="phone"
								type="number" name="phone">
						</div>

						<div class="col-sm-6 p-b-5 m-lr-auto">
							<label class="stext-102 cl3" for="name">ADDRESS</label> <input
								class="size-111 bor8 stext-102 cl2 p-lr-20" id="address"
								type="text" name="address">
						</div>

						<div class="col-sm-6 p-b-5 m-lr-auto">
							<label class="stext-102 cl3" for="name">PAYMENT</label>

							<div class="size-204 respon6-next">
								<div class="rs1-select2 bor8 bg0">
									<select class="js-select2 size-111 bor8 stext-102 cl2 p-lr-20"
										name="payment">
										<option></option>
										<option>카드</option>
										<option>무통장 입금</option>
									</select>
									<div class="dropDownSelect2"></div>
								</div>
							</div>
						</div>
						<div class="col-sm-6 p-b-5 m-lr-auto">
							<input type="submit"
								class="flex-c-m stext-101 cl2 size-119 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-10"
								value="ORDER" style="width: 300px; height: 50px">
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>

</body>
</html>