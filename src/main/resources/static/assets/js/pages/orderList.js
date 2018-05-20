$(document).ready(function() {
    $(function() {


    // <tr>
    //     <th>ID</th>
    //     <th>订单时间</th>
    //     <th>演出地点</th>
    //     <th>演出厅</th>
    //     <th>演出时间</th>
    //     <th>结束时间</th>
    //     <th>总价</th>
    //     <th>状态</th>
    //     </tr>
        doGet('/order/getUserOrder', function(data){
            $.each(data,function (key,val) {
                var dis = "disabled ";
                if(val.status=="已付款" || val.status=="未付款"){
                    dis="";
                }
                console.log(val);
                $('#tableBody').append(" <tr id='1' class=\"odd gradeX\">\n" +
                    "                                                <td>"+val.id+"</td>\n" +
                    "                                                <td>"+val.orderTime+"</td>\n" +
                    "                                                <td>"+val.hostPlace+"</td>\n" +
                    "                                                <td>"+val.place+"</td>\n" +
                    "                                                <td>"+val.performTime+"</td>\n" +
                    "                                                <td>"+val.endTime+"</td>\n" +
                    "                                                <td>"+val.totalPrice+"</td>\n" +
                    "                                                <td>"+val.status+"</td>\n" +
                    "                                <td><button "+dis+"class=\"btn btn-warning\" onclick=\"concel("+val.id+")\">取消</button></td>\n"+


                    "                                            </tr>");

            });


//------------- Extend table tools -------------//
            $.extend( true, $.fn.DataTable.TableTools.classes, {
                "container": "DTTT btn-group",
                "buttons": {
                    "normal": "btn btn-default",
                    "disabled": "disabled"
                },
                "collection": {
                    "container": "DTTT_dropdown dropdown-menu",
                    "buttons": {
                        "normal": "",
                        "disabled": "disabled"
                    }
                },
                "print": {
                    "info": "DTTT_print_info modal"
                }
            } );

// Have the collection use a bootstrap compatible dropdown
            $.extend( true, $.fn.DataTable.TableTools.DEFAULTS.oTags, {
                "collection": {
                    "container": "ul",
                    "button": "li",
                    "liner": "a"
                }
            });

//------------- Datatables -------------//
            $('#datatable').dataTable({
                "sPaginationType": "bs_full", //"bs_normal", "bs_two_button", "bs_four_button", "bs_full"
                "fnPreDrawCallback": function( oSettings ) {
                    $('.dataTables_filter input').addClass('form-control input-large').attr('placeholder', 'Search');
                    $('.dataTables_length select').addClass('form-control input-small');
                },
                "oLanguage": {
                    "sSearch": "",
                    "sLengthMenu": "<span>_MENU_ entries</span>"
                },
                "bJQueryUI": false,
                "bAutoWidth": false,
                "sDom": "<'row'<'col-lg-6 col-md-6 col-sm-12 text-center'l><'col-lg-6 col-md-6 col-sm-12 text-center'f>r>t<'row-'<'col-lg-6 col-md-6 col-sm-12'i><'col-lg-6 col-md-6 col-sm-12'p>>",
            });

            $('#datatable1').dataTable({
                "sPaginationType": "bs_full", //"bs_normal", "bs_two_button", "bs_four_button", "bs_full"
                "fnPreDrawCallback": function( oSettings ) {
                    $('.dataTables_filter input').addClass('form-control input-large').attr('placeholder', 'Search');
                    $('.dataTables_length select').addClass('form-control input-small');
                },
                "oLanguage": {
                    "sSearch": "",
                    "sLengthMenu": "<span>_MENU_ entries</span>"
                },
                "bJQueryUI": false,
                "bAutoWidth": false,
                "sDom": "<'row'<'col-lg-3 col-md-3 col-sm-12 text-center'l><'col-lg-6 col-md-6 col-sm-12 text-center'T><'col-lg-3 col-md-3 col-sm-12 text-center'f>r>t<'row-'<'col-lg-6 col-md-6 col-sm-12'i><'col-lg-6 col-md-6 col-sm-12'p>>",
                "oTableTools": {
                    "sSwfPath": "assets/plugins/tables/datatables/tabletools/swf/copy_csv_xls_pdf.swf",
                    "aButtons": [
                        {
                            "sExtends":    "print",
                            "sButtonText": '<i class="st-printer s16 vat"></i> Print',
                            "aButtons":    [ "print" ]
                        },
                        {
                            "sExtends":    "xls",
                            "sButtonText": '<i class="im-file-excel s16 vat"></i> XLS',
                            "aButtons":    [ "xls" ]
                        },
                        {
                            "sExtends":    "pdf",
                            "sButtonText": '<i class="im-file-pdf s16 vat"></i> PDF',
                            "aButtons":    [ "pdf" ]
                        },
                        {
                            "sExtends":    "csv",
                            "sButtonText": '<i class="im-file-xml s16 vat"></i> CSV',
                            "aButtons":    [ "csv" ]
                        },
                        {
                            "sExtends":    "copy",
                            "sButtonText": '<i class="im-copy s16 vat"></i> Copy',
                            "aButtons":    [ "copy" ]
                        }
                    ]
                }
            });


//------------- Sparklines -------------//
            $('#usage-sparkline').sparkline([35,46,24,56,68, 35,46,24,56,68], {
                width: '180px',
                height: '30px',
                lineColor: '#00ABA9',
                fillColor: false,
                spotColor: false,
                minSpotColor: false,
                maxSpotColor: false,
                lineWidth: 2
            });

            $('#cpu-sparkline').sparkline([22,78,43,32,55, 67,83,35,44,56], {
                width: '180px',
                height: '30px',
                lineColor: '#00ABA9',
                fillColor: false,
                spotColor: false,
                minSpotColor: false,
                maxSpotColor: false,
                lineWidth: 2
            });

            $('#ram-sparkline').sparkline([12,24,32,22,15, 17,8,23,17,14], {
                width: '180px',
                height: '30px',
                lineColor: '#00ABA9',
                fillColor: false,
                spotColor: false,
                minSpotColor: false,
                maxSpotColor: false,
                lineWidth: 2
            });

            //------------- Init pie charts -------------//
            initPieChart();


        });

//Setup easy pie charts
        var initPieChart = function() {
            $(".pie-chart").easyPieChart({
                barColor: '#5a5e63',
                borderColor: '#5a5e63',
                trackColor: '#d9dde2',
                scaleColor: false,
                lineCap: 'butt',
                lineWidth: 10,
                size: 40,
                animate: 1500
            });
        }
        // $('#tableBody').append(" <tr id='1' onclick='clickRow()' class=\"odd gradeX\">\n" +
        //     "                                                <td>订单</td>\n" +
        //     "                                                <td>123</td>\n" +
        //     "                                                <td>订单</td>\n" +
        //     "                                                <td class=\"center\">4</td>\n" +
        //     "                                                <td class=\"center\">X</td>\n" +
        //     "                                            </tr>")
    });
});



function concel(id) {
    layer.confirm('确认要取消订单吗？',{
        btn:['确认','取消'],
        btn1:function (index, layero) {
            doPost("order/cancelOrder?orderId="+id);
            window.location.reload();
            return true;

        }
    })
}

// $("table").on("click","tr",function(e){
//     var arr = []
//     $(this).children().map(function(el){
//         arr.push($(this)[0].innerText)
//     })
//     var id = arr[0];
//     console.log(arr);
//     window.location.href="performDetail.html?id="+id;
// })