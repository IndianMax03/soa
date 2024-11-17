import { Route, Routes } from 'react-router-dom';
import './App.css';
import { TicketPage } from './components/TicketPage';
import { PersonsPage } from './components/PersonsPage';
import { Layout } from './components/Layout';

function App() {
  return (
    <Layout>
      <Routes>
        <Route path="/tickets" element={<TicketPage />} />
        <Route path="/persons" element={<PersonsPage />} />
        {/* <Route path="*" element={<PageNotFound />} /> */}
      </Routes>
    </Layout>
  );
}

export default App;
