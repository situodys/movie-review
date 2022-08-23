import {BrowserRouter, Route, Router, Routes} from "react-router-dom";
import AddMovie from "./view/AddMovie";
import NavigationBar from "./component/NavigationBar";
import App from "./App";

function AppRouter() {
    return (
            <BrowserRouter>
                <div>
                    <NavigationBar/>
                    <Routes>
                        <Route path ="/register/movie" element={<AddMovie/>}></Route>
                        <Route path ="/" element={<App/>}></Route>
                    </Routes>
                </div>
            </BrowserRouter>
    )
}

export default AppRouter