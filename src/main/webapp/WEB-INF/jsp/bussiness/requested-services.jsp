<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>
<tr id="service-row-${rowIndex}">
	<td>
		<div class="form-group">
			<input type="checkbox" name="index${rowIndex}"
				id="index${rowIndex}" value="${rowIndex}" class="row-index" />
		</div>
		<input type="hidden" name="type${rowIndex}" id="type${rowIndex}" value="${serviceType}"/>
		
	</td>
	<td>
		<div class="form-group">
			<input type="text" name="name${rowIndex}"
				id="name${rowIndex}" class="form-control input-sm"
				placeholder="Service Name" /><span id="error-name${rowIndex}" class="error-label"></span>
		</div>
	</td>
	<td>
		<div class="form-group">
			<input type="text" name="serviceFee${rowIndex}"
				id="serviceFee${rowIndex}" class="form-control input-sm"
				placeholder="&#8377 0.00" style="width: 80px" /><span id="error-serviceFee${rowIndex}" class="error-label"></span>
		</div>
	</td>
</tr>
