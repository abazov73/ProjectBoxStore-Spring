import React from "react";
export default function Footer(){
    return(
        <div className="ml-0 mr-0 h-25 d-flex flex-column justify-content-end">
			<footer className="footer d-flex container-fluid">
				<div className="text-left text-nowrap ml-0 footerLeft fw-bold">
					Контакты<br/>
					+7(***)***-**-**<br/>
					+7(***)***-**-**<br/>
				</div>
				<div className="text-bottom text-center mx-auto mt-auto mb-0 fw-bold footerBottom">
					boxStore. inc, 2022
				</div>
				<div className="footerRight me-0 ms-auto my-0 d-flex flex-column">
					<a href="https://www.apple.com/ru/app-store/"><img src="AppStore.png" width="110" height="55"/></a>
					<img src="GooglePlay.png" width="110" height="55"/>
				</div>
			</footer>
		</div>
    );
}