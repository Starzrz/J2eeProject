//------------- login.js -------------//
$(document).ready(function() {

    //for custom checkboxes
    $('input').not('.noStyle').iCheck({
        checkboxClass: 'icheckbox_flat-green'
    });

    //validate login form
    $("#login-form").validate({
        ignore: null,
        ignore: 'input[type="hidden"]',
        errorPlacement: function(error, element) {
            wrap = element.parent();
            wrap1 = wrap.parent();
            if (wrap1.hasClass('checkbox')) {
                error.insertAfter(wrap1);
            } else {
                if (element.attr('type')=='file') {
                    error.insertAfter(element.next());
                } else {
                    error.insertAfter(element);
                }
            }
        },
        errorClass: 'help-block',
        rules: {
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 5
            }
        },
        messages: {
            password: {
                required: "请输入密码",
                minlength: "密码不得少于5位"
            },
            email: "请输入有效邮箱",
        },
        highlight: function(element) {
            if ($(element).offsetParent().parent().hasClass('form-group')) {
                $(element).offsetParent().parent().removeClass('has-success').addClass('has-error');
            } else {
                if ($(element).attr('type')=='file') {
                    $(element).parent().parent().removeClass('has-success').addClass('has-error');
                }
                $(element).offsetParent().parent().parent().parent().removeClass('has-success').addClass('has-error');

            }
        },
        unhighlight: function(element,errorClass) {
            if ($(element).offsetParent().parent().hasClass('form-group')) {
                $(element).offsetParent().parent().removeClass('has-error').addClass('has-success');
                $(element.form).find("label[for=" + element.id + "]").removeClass(errorClass);
            } else if ($(element).offsetParent().parent().hasClass('checkbox')) {
                $(element).offsetParent().parent().parent().parent().removeClass('has-error').addClass('has-success');
                $(element.form).find("label[for=" + element.id + "]").removeClass(errorClass);
            } else if ($(element).next().hasClass('bootstrap-filestyle')) {
                $(element).parent().parent().removeClass('has-error').addClass('has-success');
            }
            else {
                $(element).offsetParent().parent().parent().removeClass('has-error').addClass('has-success');
            }
        }
    });
    $("#hostRegister").validate({
        ignore: null,
        ignore: 'input[type="hidden"]',
        errorPlacement: function(error, element) {
            wrap = element.parent();
            wrap1 = wrap.parent();
            if (wrap1.hasClass('checkbox')) {
                error.insertAfter(wrap1);
            } else {
                if (element.attr('type')=='file') {
                    error.insertAfter(element.next());
                } else {
                    error.insertAfter(element);
                }
            }
        },
        errorClass: 'help-block',
        rules: {
            name: {
                required: true
            },
            place: {
                required: true

            },
            xsites:{
                required:true,
                digits:true,
                range:[1,20]

            },
            ysites:{
                required:true,
                digits:true,
                range:[1,20]

            },
            vip1:{
                required:true,
                digits:true,
                range:[1,20]
            },
            vip2:{
                required:true,
                digits:true,
                range:[1,20]
            }
        },
        messages: {
            name:"请输入名称",
            place:"请输入地点",
            xsites:{
                required:"请输入排数",
                digits:"请输入数字",
                range:"不得超过20"

            },
            ysites:{
                required:"请输入列数",
                digits:"请输入数字",
                range:"座位不存在"

            },
            vip1:{
                required:"请输入排数",
                digits:"请输入数字",
                range:"座位不存在"

            },
            vip2:{
                required:"请输入排数",
                digits:"请输入数字",
                range:"座位不存在"

            },
        },
        highlight: function(element) {
            if ($(element).offsetParent().parent().hasClass('form-group')) {
                $(element).offsetParent().parent().removeClass('has-success').addClass('has-error');
            } else {
                if ($(element).attr('type')=='file') {
                    $(element).parent().parent().removeClass('has-success').addClass('has-error');
                }
                $(element).offsetParent().parent().parent().parent().removeClass('has-success').addClass('has-error');

            }
        },
        unhighlight: function(element,errorClass) {
            if ($(element).offsetParent().parent().hasClass('form-group')) {
                $(element).offsetParent().parent().removeClass('has-error').addClass('has-success');
                $(element.form).find("label[for=" + element.id + "]").removeClass(errorClass);
            } else if ($(element).offsetParent().parent().hasClass('checkbox')) {
                $(element).offsetParent().parent().parent().parent().removeClass('has-error').addClass('has-success');
                $(element.form).find("label[for=" + element.id + "]").removeClass(errorClass);
            } else if ($(element).next().hasClass('bootstrap-filestyle')) {
                $(element).parent().parent().removeClass('has-error').addClass('has-success');
            }
            else {
                $(element).offsetParent().parent().parent().removeClass('has-error').addClass('has-success');
            }
        }
    });
    $("#register-form").validate({
        ignore: null,
        ignore: 'input[type="hidden"]',
        errorPlacement: function(error, element) {
            wrap = element.parent();
            wrap1 = wrap.parent();
            if (wrap1.hasClass('checkbox')) {
                error.insertAfter(wrap1);
            } else {
                if (element.attr('type')=='file') {
                    error.insertAfter(element.next());
                } else {
                    error.insertAfter(element);
                }
            }
        },
        errorClass: 'help-block',
        rules: {
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 5,
                equalTo:"#confirm_password"
            },

        },
        messages: {
            password: {
                required: "请输入密码",
                minlength: "密码不得少于5位",
                equalTo:"请输入相同的密码"
            },
            email: "请输入有效邮箱",
        },
        highlight: function(element) {
            if ($(element).offsetParent().parent().hasClass('form-group')) {
                $(element).offsetParent().parent().removeClass('has-success').addClass('has-error');
            } else {
                if ($(element).attr('type')=='file') {
                    $(element).parent().parent().removeClass('has-success').addClass('has-error');
                }
                $(element).offsetParent().parent().parent().parent().removeClass('has-success').addClass('has-error');

            }
        },
        unhighlight: function(element,errorClass) {
            if ($(element).offsetParent().parent().hasClass('form-group')) {
                $(element).offsetParent().parent().removeClass('has-error').addClass('has-success');
                $(element.form).find("label[for=" + element.id + "]").removeClass(errorClass);
            } else if ($(element).offsetParent().parent().hasClass('checkbox')) {
                $(element).offsetParent().parent().parent().parent().removeClass('has-error').addClass('has-success');
                $(element.form).find("label[for=" + element.id + "]").removeClass(errorClass);
            } else if ($(element).next().hasClass('bootstrap-filestyle')) {
                $(element).parent().parent().removeClass('has-error').addClass('has-success');
            }
            else {
                $(element).offsetParent().parent().parent().removeClass('has-error').addClass('has-success');
            }
        }
    });

});