CREATE TABLE loan (
  id INT PRIMARY KEY AUTO_INCREMENT,
  borrower_name VARCHAR(100),
  loan_number VARCHAR(50) UNIQUE,
  current_stage VARCHAR(50),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE registered (
  id INT PRIMARY KEY AUTO_INCREMENT,
  loan_id INT,
  completed_by VARCHAR(100),
  lead_source VARCHAR(100),
  registration_date DATE,
  loan_purpose VARCHAR(50),
  property_state VARCHAR(2),
  expected_closing_date DATE,
  appraisal_deadline DATE,
  condition_deadline DATE,
  funding_date DATE,
  notes TEXT,
  date_completed TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE application (
  id INT PRIMARY KEY AUTO_INCREMENT,
  loan_id INT,
  completed_by VARCHAR(100),
  application_date DATE,
  loan_amount DECIMAL(12,2),
  loan_type VARCHAR(50),
  occupancy_type VARCHAR(50),
  property_type VARCHAR(50),
  borrower_email VARCHAR(100),
  notes TEXT,
  date_completed TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE disclosures (
  id INT PRIMARY KEY AUTO_INCREMENT,
  loan_id INT,
  completed_by VARCHAR(100),
  initial_disclosures_sent_date DATE,
  initial_disclosures_signed_date DATE,
  intent_to_proceed_date DATE,
  econsent_received BOOLEAN,
  re_disclosures_required BOOLEAN,
  re_disclosures_sent_date DATE,
  notes TEXT,
  date_completed TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE processing (
  id INT PRIMARY KEY AUTO_INCREMENT,
  loan_id INT,
  completed_by VARCHAR(100),
  processing_start_date DATE,
  voe_ordered_date DATE,
  voe_received_date DATE,
  title_ordered_date DATE,
  title_received_date DATE,
  homeowners_insurance_received_date DATE,
  appraisal_ordered_date DATE,
  appraisal_received_date DATE,
  submission_to_underwriting_date DATE,
  notes TEXT,
  date_completed TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE underwriting (
  id INT PRIMARY KEY AUTO_INCREMENT,
  loan_id INT,
  underwriter_name VARCHAR(100),
  submission_date DATE,
  decision_date DATE,
  decision_result VARCHAR(50),
  credit_reviewed BOOLEAN,
  credit_cleared BOOLEAN,
  credit_notes TEXT,
  appraisal_reviewed BOOLEAN,
  appraisal_cleared BOOLEAN,
  appraisal_notes TEXT,
  income_reviewed BOOLEAN,
  income_cleared BOOLEAN,
  income_notes TEXT,
  assets_reviewed BOOLEAN,
  assets_cleared BOOLEAN,
  assets_notes TEXT,
  general_notes TEXT,
  date_completed TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE approved (
  id INT PRIMARY KEY AUTO_INCREMENT,
  loan_id INT,
  approved_by VARCHAR(100),
  approval_date DATE,
  cd_issued BOOLEAN,
  cd_issued_date DATE,
  cd_signed BOOLEAN,
  cd_signed_date DATE,
  estimated_docs_out DATE,
  approval_notes TEXT,
  date_completed TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE ctc (
  id INT PRIMARY KEY AUTO_INCREMENT,
  loan_id INT,
  ctc_issued_by VARCHAR(100),
  ctc_issued_date DATE,
  all_conditions_cleared BOOLEAN,
  cd_signed BOOLEAN,
  cd_signed_date DATE,
  closing_scheduled_date DATE,
  ctc_notes TEXT,
  date_completed TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE funded (
  id INT PRIMARY KEY AUTO_INCREMENT,
  loan_id INT,
  funded_by VARCHAR(100),
  funded_date DATE,
  funding_method VARCHAR(50),
  wire_confirmed BOOLEAN,
  wire_confirmed_date DATE,
  final_disbursement_amount DECIMAL(12,2),
  final_closing_disclosure_sent BOOLEAN,
  final_cd_sent_date DATE,
  funding_notes TEXT,
  date_completed TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE registered ADD CONSTRAINT fk_registered_loan FOREIGN KEY (loan_id) REFERENCES loan(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE application ADD CONSTRAINT fk_application_loan FOREIGN KEY (loan_id) REFERENCES loan(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE disclosures ADD CONSTRAINT fk_disclosures_loan FOREIGN KEY (loan_id) REFERENCES loan(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE processing ADD CONSTRAINT fk_processing_loan FOREIGN KEY (loan_id) REFERENCES loan(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE underwriting ADD CONSTRAINT fk_underwriting_loan FOREIGN KEY (loan_id) REFERENCES loan(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE approved ADD CONSTRAINT fk_approved_loan FOREIGN KEY (loan_id) REFERENCES loan(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE ctc ADD CONSTRAINT fk_ctc_loan FOREIGN KEY (loan_id) REFERENCES loan(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE funded ADD CONSTRAINT fk_funded_loan FOREIGN KEY (loan_id) REFERENCES loan(id) ON DELETE CASCADE ON UPDATE CASCADE;