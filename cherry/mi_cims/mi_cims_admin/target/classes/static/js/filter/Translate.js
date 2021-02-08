"use strict"

/**
 * 多语言转化过滤器
 */
app.filter("T", ['$translate', function($translate) {  
    return function(text, key) {  
        if(key){  
            return $translate.instant(key);
        }  
    };  
}]); 