<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" errorPage="errors.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="bibSource" class="edu.ucla.library.libservices.aeon.vger.generators.VgerBibDataGenerator">
    <jsp:setProperty name="bibSource" property="bibID" param="bibID"/>
    <jsp:setProperty name="bibSource" property="dbName"
                     value='<%= application.getInitParameter("datasource.vger") %>'/>
</jsp:useBean>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link rel="shortcut icon" href="./images/favicon.ico" type="image/x-icon"/>
        <link rel="icon" href="./images/favicon.ico" type="image/x-icon"/>
        <link rel="stylesheet" href="bootstrap-4.3.1-dist/css/bootstrap.css"/>
        <link rel="stylesheet" href="css/main.css"/>
        <title>Aeon UCLA Library - Submit a Special Collections Request</title>
    </head>
    <body class="d-flex flex-column">
        <section class="site-navbar">
            <nav class="navbar navbar-light nav-primary">
                <a href="#">
                    <div class="navbar-brand py-0">
                        <img src="images/ucla-library-logo-wht.svg" class="" alt="UCLA Library logo"/>
                         
                        <span class="site-title">Special Collections Request System</span>
                    </div>
                </a>
            </nav>
            <nav class="nav nav-secondary flex-column flex-md-row pl-3 py-2">
                <a class="nav-link" href="http://www.library.ucla.edu/special-collections" target="_blank">Special
                                                                                                           Collections
                                                                                                           Home</a>
                <a class="nav-link" href="https://catalog.library.ucla.edu/" target="_blank">UCLA Library Catalog</a>
                <a class="nav-link" href="http://www.oac.cdlib.org/" target="_blank">Online Archive of California (OAC)</a>
                <a class="nav-link"
                   href="https://www.library.ucla.edu/special-collections/frequently-asked-questions-faq"
                   target="_blank">FAQs</a>
            </nav>
        </section>
         
        <main class="container my-5 flex-grow-1">
            <section class="pg-welcome shadow-sm rounded p-md-5 p-4">
                <p>Special Collections materials do not circulate and can only be consulted in one of our reading rooms.</p>
                <ul>
                    <li>Research Library Special Collections materials are accessed in the Ahmanson-Murphy reading room.</li>
                    <li>History and Special Collections for the Sciences materials are accessed in the Biomedical
                        Library History and Special Collections for Medicine and the Sciences reading room.</li>
                </ul>
                <p>Please select your requested materials and provide a date for your research visit. We do not accept
                   appointments and we are unable to guarantee seating arrangements.</p>
                <p>Due to space constraints, the following paging limits are enforced:</p>
                <ul>
                    <li>
                        <b>Retrieval of off-site holdings is limited to 5 boxes/items per day</b>
                    </li>
                    <li>
                        <b>Retrieval of on-site holdings is limited to 5 boxes/items per hour</b>
                    </li>
                </ul>
                <p>
                    If you would like to see the material in a particular order, please indicate this in the 
                    <strong>&ldquo;Special Requests&rdquo;</strong>
                     field.
                </p>
                <p>
                    You can review your selections by clicking the 
                    <strong>&ldquo;Confirm Request&rdquo;</strong>
                     button.
                </p>
            </section>
            <c:set var="bibRecord" value="${bibSource.bibData}"/>
            <form action="submit.jsp" id="bibForm" method="POST" onsubmit="JavaScript:return validateForm(this);">
                <input type="hidden" name="bibID" value="${param.bibID}"/>
                <!-- Request Information -->
                <section class="req-info">
                    <!-- Table with 50-50 columns, Single header -->
                    <div class="table table-50-50">
                        <div class="t-title text-center text-white py-1">Request Information</div>
                        <!-- Start of row -->
                        <div class="t-row">
                            <div class="t-cell py-1 px-3 column-1">Call Number(s)</div>
                            <div class="t-cell py-1 px-3 column-2">
                                <ul>
                                    <c:forEach var="yrl" items="${bibRecord.yrlHoldings}">
                                        <li>
                                            <c:if test="${not empty yrl.callNo}">
                                                ${yrl.callNo}
                                            </c:if>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                        <!-- End of row -->
                        <!-- Start of row -->
                        <div class="t-row">
                            <div class="t-cell py-1 px-3 column-1">Author</div>
                            <div class="t-cell py-1 px-3 column-2">
                                ${bibRecord.author}
                            </div>
                        </div>
                        <!-- End of row -->
                        <!-- Start of row -->
                        <div class="t-row">
                            <div class="t-cell py-1 px-3 column-1">Title</div>
                            <div class="t-cell py-1 px-3 column-2">
                                ${bibRecord.title}
                            </div>
                        </div>
                        <!-- End of row -->
                        <!-- Start of row -->
                        <div class="t-row">
                            <div class="t-cell py-1 px-3 column-1">Dates</div>
                            <div class="t-cell py-1 px-3 column-2">
                                ${bibRecord.pubDates}
                            </div>
                        </div>
                        <!-- End of row -->
                        <!-- Start of row -->
                        <div class="t-row">
                            <div class="t-cell py-1 px-3 column-1">Location</div>
                            <div class="t-cell py-1 px-3 column-2">???</div>
                        </div>
                        <!-- End of row -->
                    </div>
                    <!--End of table -->
                    <hr/>
                </section>
                <c:choose>
                    <c:when test="${not empty bibRecord.srlfItems}">
                        <!-- Request Material -->
                        <section class="req-materials">
                            <!-- Table with column 1 at 12%, column 2 at 88% -->
                            <div class="table table-12-88">
                                <!-- Start of table heading -->
                                <div class="t-heading text-center">
                                    <div class="t-cell py-2">
                                        Select
                                    </div>
                                    <div class="t-cell py-2">Item Description</div>
                                </div>
                                <!-- End of table heading -->
                                <c:forEach var="srlf" items="${bibRecord.srlfItems}">
                                    <!-- Start of row -->
                                    <div class="t-row">
                                        <div class="t-cell py-1 text-center column-1">
                                            <%-- input type="checkbox" name="itemID" id="item.${srlf.itemID}"
                                                 value="${srlf.itemID}"/--%>
                                             
                                            <c:choose>
                                                <c:when test="${not empty srlf.itemID}">
                                                    <input type="checkbox" name="itemID" id="item.${srlf.itemID}"
                                                           value="${srlf.itemID}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="checkbox" name="itemID" id="item.${srlf.callNo}"
                                                           value="${srlf.callNo}"/>
                                                </c:otherwise>
                                            </c:choose>
                                             
                                            <%-- c:if test="${(not empty srlf.statusID) and (srlf.statusID ne 1)}">
                                                 <br/> (${srlf.status}; There may be a delay in retrieval) </c:if--%>
                                        </div>
                                        <div class="t-cell py-1 px-3 column-2">
                                            <label for="item.${srlf.itemID}">
                                                <c:if test="${not empty srlf.itemEnum}">
                                                    ${srlf.itemEnum}
                                                    &nbsp;|&nbsp;
                                                </c:if>
                                                <c:if test="${not empty srlf.chron}">
                                                    ${srlf.chron}
                                                    &nbsp;|&nbsp;
                                                </c:if>
                                                <c:if test="${(not empty srlf.copy) and (srlf.copy ne 0)}">
                                                    Copy&nbsp;
                                                    ${srlf.copy}
                                                    &nbsp;|&nbsp;
                                                </c:if>
                                                <c:if test="${not empty srlf.note}">
                                                    ${srlf.note}
                                                    &nbsp;|&nbsp;
                                                </c:if>
                                                <c:if test="${not empty srlf.callNo}">
                                                    ${srlf.callNo}
                                                    &nbsp;|&nbsp;
                                                </c:if>
                                                <c:if test="${not empty srlf.freeText}">
                                                    ${srlf.freeText}
                                                    &nbsp;|&nbsp;
                                                </c:if>
                                                <c:if test="${not empty srlf.oacDetails}">
                                                    
                                                    ${srlf.oacDetails}
                                                </c:if>
                                                <c:if test="${not empty srlf.pickupLocale}">
                                                    &nbsp;|&nbsp;
                                                    ${srlf.pickupLocale}
                                                </c:if>
                                            </label>
                                        </div>
                                    </div>
                                    <!-- End of row -->
                                </c:forEach>
                            </div>
                            <!--End of table-10-90 -->
                            <hr/>
                        </section>
                        <!-- Request Type -->
                        <section class="req-type">
                            <h4 class="h4-blue mb-4">Select Request Type</h4>
                            <div class="req-type-row">
                                <div class="req-type-box">
                                    <input type="radio" name="reqType" id="reqType.1" value="1" checked="checked"
                                           class="" onclick="toggle_visibility('dupe', 'visit',1);"/>
                                     
                                    <label for="reqType.1" class="">On-site review</label>
                                    <p>
                                        <em>Request item for Reading Room use</em>
                                    </p>
                                </div>
                                <div class="req-type-box">
                                    <input type="radio" name="reqType" id="reqType.2" value="2"
                                           onclick="toggle_visibility('dupe', 'visit',2);"/>
                                     
                                    <label for="reqType.2">Duplication</label>
                                    <p>
                                        <em>Submit duplication orders</em>
                                    </p>
                                </div>
                            </div>
                            <div id="dupe" class="mt-4 p-4 toggled-box">
                                <p>Duplication requests are processed in the order received and generally take 2-4 weeks
                                   to complete. Duplication Services staff will contact you to confirm your request when
                                   your order is ready to be processed.</p>
                                <p class="mb-3 mb-md-4">
                                    To view our fee schedule and policies, please visit: 
                                    <a href="http://library.ucla.edu/use/access-privileges/print-copy-scan/special-collections-reproductions"
                                       target="_blank">http://library.ucla.edu/use/access-privileges/print-copy-scan/special-collections-reproductions</a>
                                </p>
                                <label for="Format">
                                    <h5 class="h5-blue">Format:</h5>
                                </label>
                                 
                                <br/>
                                <div class="input-row">
                                    <span>
                                        <input type="radio" name="reqType" id="reqType.2" value="AV"/><label>Audio /
                                                                                                             Video</label></span>
                                     
                                    <span>
                                        <input type="radio" name="reqType" id="reqType.2" value="PDF"/>
                                         
                                        <label>PDF</label></span>
                                     
                                    <span>
                                        <input type="radio" name="reqType" id="reqType.2" value="TIFF"/><label>TIFF</label></span>
                                </div>
                                <hr/>
                                <input type="checkbox" name="ForPublication" id="ForPublication" value="Yes"/>
                                 
                                <label for="ForPublication">For Publication?</label>
                                <hr/>
                                <label for="ItemInfo3">Project Description</label><br/>
                                 
                                <textarea cols="45" rows="5" id="ItemInfo3" name="ItemInfo3" disabled="true"></textarea>
                                 
                                <input value="Copy" name="RequestType" id="RequestType" type="hidden" disabled="true"/>
                                 
                                <input type="hidden" name="SkipOrderEstimate" id="SkipOrderEstimate" value="Yes"
                                       disabled="true"/>
                            </div>
                            <hr/>
                            <!--  -->
                            <c:choose>
                                <c:when test="${not empty param.theDate}">
                                    <input name="ScheduledDate" type="hidden" value="${param.theDate}"/>
                                </c:when>
                                <c:otherwise>
                                    <input name="ScheduledDate" type="hidden" value="${param.textDate}"/>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${bibSource.isBio}">
                                    <input name="Site" type="hidden" value="BIOMED"/>
                                </c:when>
                                <c:otherwise>
                                    <input name="Site" type="hidden" value="YRL"/>
                                </c:otherwise>
                            </c:choose>
                            <input type="hidden" name="ItemAuthor" value="${bibRecord.author}"/>
                            <input type="hidden" name="ItemDate" value="${bibRecord.pubDates}"/>
                            <input type="hidden" name="SubmitButton" value="Submit Request"/>
                            <input type="hidden" name="WebRequestForm" value="DefaultRequest"/>
                            <c:choose>
                                <c:when test="${not empty bibRecord.srlfItems}">
                                    <c:set var="index" value="-1"/>
                                    <c:forEach var="srlf" items="${bibRecord.srlfItems}" begin="0" end="0">
                                        <input value="${srlf.location}" name="Location" type="hidden"/>
                                    </c:forEach>
                                    <!--  -->
                                </c:when>
                            </c:choose>
                        </section>
                        <!-- Date of Visit -->
                        <section class="req-date" id="visit" name="Visit">
                            <jsp:useBean id="dateGetter"
                                         class="edu.ucla.library.libservices.aeon.vger.utility.AvailableDates">
                                <c:choose>
                                    <c:when test="${bibSource.isBio}">
                                        <jsp:setProperty name="dateGetter" property="unitID"
                                                         value='<%= application.getInitParameter("unitid.biohis") %>'/>
                                    </c:when>
                                    <c:otherwise>
                                        <jsp:setProperty name="dateGetter" property="unitID"
                                                         value='<%= application.getInitParameter("unitid.yrlspc") %>'/>
                                    </c:otherwise>
                                </c:choose>
                                <%-- jsp:setProperty name="dateGetter" property="dbName" value='<%=
                                     application.getInitParameter("datasource.hours") %>'/--%>
                            </jsp:useBean>
                            <c:set var="openDates" value="${dateGetter.availables}"/>
                            <h4 class="h4-blue">Select Date of Visit</h4>
                            <p class="text-danger pb-0 mb-0">
                                <em>Required</em>
                            </p>
                            <p>We cannot accommodate same day requests for off-site materials. Please allow at least 3
                               days for retrieval of off-site materials.</p>
                            <div class="req-date-row text-center">
                                <div class="date-box date-picker pl-3 pt-3">
                                    <p>Select a listed date</p>
                                    <div class="date-col-wrapper">
                                        <div class="date-col1">
                                            <div class="date-item">
                                                <c:forEach var="theDate" items="${openDates}" begin="0" end="6">
                                                    <input type="radio" name="theDate" id="theDate.${theDate}"
                                                           value="${theDate}"/>
                                                    &nbsp;
                                                    <label for="theDate.${theDate}">
                                                        ${theDate}
                                                    </label>
                                                    <br/>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="date-col2">
                                            <div class="date-item">
                                                <c:forEach var="theDate" items="${openDates}" begin="7" end="13">
                                                    <input type="radio" name="theDate" id="theDate.${theDate}"
                                                           value="${theDate}"/>
                                                    &nbsp;
                                                    <label for="theDate.${theDate}">
                                                        ${theDate}
                                                    </label>
                                                    <br/>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <p class="mid-col pt-3">
                                    <b>OR</b>
                                </p>
                                <div class="date-box date-input pl-3 pt-3">
                                    <p>Enter your preferred date</p>
                                    <input name="textDate" id="textDate" value="" onchange="clearRadioDates();"
                                           placeholder="MM/DD/YYYY"/>
                                </div>
                            </div>
                            <hr/>
                        </section>
                        <!-- Special Requests -->
                        <section class="req-notes">
                            <h4 class="h4-blue">Special Requests</h4>
                            <p class="text-muted pb-0 mb-0">
                                <em>Optional</em>
                            </p>
                            <label class="pt-0 pb-2" for="specReq">Please enter any special requests or questions you
                                                                   have for library staff.</label>
                            <br/>
                            <textarea cols="45" rows="5" name="specReq" id="specReq" maxlength="255"
                                      placeholder="Maximum 255 characters"></textarea>
                            <hr/>
                        </section>
                        <!--  -->
                        <!--  -->
                        <input type="submit" value="Submit Request"/>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Confirm Request"/>
                    </c:otherwise>
                </c:choose>
            </form>
        </main>
         
        <footer class="p-4">
            <small class="text-white">Copyright &copy; 2019 UC Regents. All Rights Reserved.</small>
        </footer>
        <script src="js/toggle.js" type="text/javascript" language="JavaScript"></script>
        <script src="js/validateForm.js" type="text/javascript" language="JavaScript"></script>
    </body>
</html>
<!--h3>Viewing bib and holdings for ${param.bibID}</h3-->
<%-- ul> <li>title:&nbsp;${bibRecord.title}</li> <li>marc246 label:&nbsp;${bibRecord.marc246}</li> <li>pub
     dates:&nbsp;${bibRecord.pubDates}</li> <li>marc506 label:&nbsp;${bibRecord.marc506}</li>
     <li>author:&nbsp;${bibRecord.author}</li> <li>marc524 label:&nbsp;${bibRecord.marc524}</li> <li>marc590
     label:&nbsp;${bibRecord.marc590}</li> </ul> <h3>YRL Holdings</h3> <ul> <c:forEach var="yrl"
     items="${bibRecord.yrlHoldings}"> <li>${yrl.callNo}</li> </c:forEach> </ul> <h3>SRLF Holdings</h3> <ul> <c:forEach
     var="srlf" items="${bibRecord.srlfItems}"> <li> <ul> <li>Item enumeration:&nbsp;${srlf.itemEnum}</li>
     <li>Chron:&nbsp;${srlf.chron}</li> <li>Copy:&nbsp;${srlf.copy}</li> <li>Barcode:&nbsp;${srlf.barcode}</li> </ul>
     </li> </c:forEach> </ul--%>
