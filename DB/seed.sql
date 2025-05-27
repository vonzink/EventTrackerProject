-- Clean tables first
SET FOREIGN_KEY_CHECKS = 0;
DELETE FROM status;
DELETE FROM documentation;
DELETE FROM funded;
DELETE FROM declined;
DELETE FROM closed;
DELETE FROM approved;
DELETE FROM application;
DELETE FROM borrower;
DELETE FROM user;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO user (id, first_name, last_name, email, username, password, role, created_at)
VALUES
(5, 'First5', 'Last5', 'user5@example.com', 'user5', 'pass5', 'LOAN_OFFICER', '2024-01-06'),
(6, 'First6', 'Last6', 'user6@example.com', 'user6', 'pass6', 'LOAN_OFFICER', '2024-01-07'),
(7, 'First7', 'Last7', 'user7@example.com', 'user7', 'pass7', 'LOAN_OFFICER', '2024-01-08'),
(8, 'First8', 'Last8', 'user8@example.com', 'user8', 'pass8', 'LOAN_OFFICER', '2024-01-09'),
(9, 'First9', 'Last9', 'user9@example.com', 'user9', 'pass9', 'LOAN_OFFICER', '2024-01-10'),
(10, 'First10', 'Last10', 'user10@example.com', 'user10', 'pass10', 'LOAN_OFFICER', '2024-01-11'),
(11, 'First11', 'Last11', 'user11@example.com', 'user11', 'pass11', 'LOAN_OFFICER', '2024-01-12'),
(12, 'First12', 'Last12', 'user12@example.com', 'user12', 'pass12', 'LOAN_OFFICER', '2024-01-13'),
(13, 'First13', 'Last13', 'user13@example.com', 'user13', 'pass13', 'LOAN_OFFICER', '2024-01-14'),
(14, 'First14', 'Last14', 'user14@example.com', 'user14', 'pass14', 'LOAN_OFFICER', '2024-01-15'),
(15, 'First15', 'Last15', 'user15@example.com', 'user15', 'pass15', 'LOAN_OFFICER', '2024-01-16'),
(16, 'First16', 'Last16', 'user16@example.com', 'user16', 'pass16', 'LOAN_OFFICER', '2024-01-17'),
(17, 'First17', 'Last17', 'user17@example.com', 'user17', 'pass17', 'LOAN_OFFICER', '2024-01-18'),
(18, 'First18', 'Last18', 'user18@example.com', 'user18', 'pass18', 'LOAN_OFFICER', '2024-01-19'),
(19, 'First19', 'Last19', 'user19@example.com', 'user19', 'pass19', 'LOAN_OFFICER', '2024-01-20'),
(20, 'First20', 'Last20', 'user20@example.com', 'user20', 'pass20', 'LOAN_OFFICER', '2024-01-21'),
(21, 'First21', 'Last21', 'user21@example.com', 'user21', 'pass21', 'LOAN_OFFICER', '2024-01-22'),
(22, 'First22', 'Last22', 'user22@example.com', 'user22', 'pass22', 'LOAN_OFFICER', '2024-01-23'),
(23, 'First23', 'Last23', 'user23@example.com', 'user23', 'pass23', 'LOAN_OFFICER', '2024-01-24'),
(24, 'First24', 'Last24', 'user24@example.com', 'user24', 'pass24', 'LOAN_OFFICER', '2024-01-25');

INSERT INTO borrower (id, first_name, last_name, email, phone, created_at)
VALUES
(5, 'Borrower5', 'Test5', 'borrower5@example.com', '555-1005', '2024-01-06'),
(6, 'Borrower6', 'Test6', 'borrower6@example.com', '555-1006', '2024-01-07'),
(7, 'Borrower7', 'Test7', 'borrower7@example.com', '555-1007', '2024-01-08'),
(8, 'Borrower8', 'Test8', 'borrower8@example.com', '555-1008', '2024-01-09'),
(9, 'Borrower9', 'Test9', 'borrower9@example.com', '555-1009', '2024-01-10'),
(10, 'Borrower10', 'Test10', 'borrower10@example.com', '555-10010', '2024-01-11'),
(11, 'Borrower11', 'Test11', 'borrower11@example.com', '555-10011', '2024-01-12'),
(12, 'Borrower12', 'Test12', 'borrower12@example.com', '555-10012', '2024-01-13'),
(13, 'Borrower13', 'Test13', 'borrower13@example.com', '555-10013', '2024-01-14'),
(14, 'Borrower14', 'Test14', 'borrower14@example.com', '555-10014', '2024-01-15'),
(15, 'Borrower15', 'Test15', 'borrower15@example.com', '555-10015', '2024-01-16'),
(16, 'Borrower16', 'Test16', 'borrower16@example.com', '555-10016', '2024-01-17'),
(17, 'Borrower17', 'Test17', 'borrower17@example.com', '555-10017', '2024-01-18'),
(18, 'Borrower18', 'Test18', 'borrower18@example.com', '555-10018', '2024-01-19'),
(19, 'Borrower19', 'Test19', 'borrower19@example.com', '555-10019', '2024-01-20'),
(20, 'Borrower20', 'Test20', 'borrower20@example.com', '555-10020', '2024-01-21'),
(21, 'Borrower21', 'Test21', 'borrower21@example.com', '555-10021', '2024-01-22'),
(22, 'Borrower22', 'Test22', 'borrower22@example.com', '555-10022', '2024-01-23'),
(23, 'Borrower23', 'Test23', 'borrower23@example.com', '555-10023', '2024-01-24'),
(24, 'Borrower24', 'Test24', 'borrower24@example.com', '555-10024', '2024-01-25');

INSERT INTO application (id, borrower_id, user_id, loan_amount, loan_type, purpose, status, property_address, submitted_date)
VALUES
(5, 5, 5, 300000 + 5 * 1000, 'Conventional', 'Purchase', 'Started', '1235 Elm St, City, CO', '2024-01-06'),
(6, 6, 6, 300000 + 6 * 1000, 'Conventional', 'Purchase', 'Started', '1236 Elm St, City, CO', '2024-01-07'),
(7, 7, 7, 300000 + 7 * 1000, 'Conventional', 'Purchase', 'Started', '1237 Elm St, City, CO', '2024-01-08'),
(8, 8, 8, 300000 + 8 * 1000, 'Conventional', 'Purchase', 'Started', '1238 Elm St, City, CO', '2024-01-09'),
(9, 9, 9, 300000 + 9 * 1000, 'Conventional', 'Purchase', 'Started', '1239 Elm St, City, CO', '2024-01-10'),
(10, 10, 10, 300000 + 10 * 1000, 'Conventional', 'Purchase', 'Started', '12310 Elm St, City, CO', '2024-01-11'),
(11, 11, 11, 300000 + 11 * 1000, 'Conventional', 'Purchase', 'Started', '12311 Elm St, City, CO', '2024-01-12'),
(12, 12, 12, 300000 + 12 * 1000, 'Conventional', 'Purchase', 'Started', '12312 Elm St, City, CO', '2024-01-13'),
(13, 13, 13, 300000 + 13 * 1000, 'Conventional', 'Purchase', 'Started', '12313 Elm St, City, CO', '2024-01-14'),
(14, 14, 14, 300000 + 14 * 1000, 'Conventional', 'Purchase', 'Started', '12314 Elm St, City, CO', '2024-01-15'),
(15, 15, 15, 300000 + 15 * 1000, 'Conventional', 'Purchase', 'Started', '12315 Elm St, City, CO', '2024-01-16'),
(16, 16, 16, 300000 + 16 * 1000, 'Conventional', 'Purchase', 'Started', '12316 Elm St, City, CO', '2024-01-17'),
(17, 17, 17, 300000 + 17 * 1000, 'Conventional', 'Purchase', 'Started', '12317 Elm St, City, CO', '2024-01-18'),
(18, 18, 18, 300000 + 18 * 1000, 'Conventional', 'Purchase', 'Started', '12318 Elm St, City, CO', '2024-01-19'),
(19, 19, 19, 300000 + 19 * 1000, 'Conventional', 'Purchase', 'Started', '12319 Elm St, City, CO', '2024-01-20'),
(20, 20, 20, 300000 + 20 * 1000, 'Conventional', 'Purchase', 'Started', '12320 Elm St, City, CO', '2024-01-21'),
(21, 21, 21, 300000 + 21 * 1000, 'Conventional', 'Purchase', 'Started', '12321 Elm St, City, CO', '2024-01-22'),
(22, 22, 22, 300000 + 22 * 1000, 'Conventional', 'Purchase', 'Started', '12322 Elm St, City, CO', '2024-01-23'),
(23, 23, 23, 300000 + 23 * 1000, 'Conventional', 'Purchase', 'Started', '12323 Elm St, City, CO', '2024-01-24'),
(24, 24, 24, 300000 + 24 * 1000, 'Conventional', 'Purchase', 'Started', '12324 Elm St, City, CO', '2024-01-25');

INSERT INTO status (id, application_id, status, changed_by_id, changed_at, notes)
VALUES
(5, 5, 'Submitted', 5, '2025-05-27 01:09:01', 'Initial status for application 5'),
(6, 6, 'Submitted', 6, '2025-05-27 01:09:01', 'Initial status for application 6'),
(7, 7, 'Submitted', 7, '2025-05-27 01:09:01', 'Initial status for application 7'),
(8, 8, 'Submitted', 8, '2025-05-27 01:09:01', 'Initial status for application 8'),
(9, 9, 'Submitted', 9, '2025-05-27 01:09:01', 'Initial status for application 9'),
(10, 10, 'Submitted', 10, '2025-05-27 01:09:01', 'Initial status for application 10'),
(11, 11, 'Submitted', 11, '2025-05-27 01:09:01', 'Initial status for application 11'),
(12, 12, 'Submitted', 12, '2025-05-27 01:09:01', 'Initial status for application 12'),
(13, 13, 'Submitted', 13, '2025-05-27 01:09:01', 'Initial status for application 13'),
(14, 14, 'Submitted', 14, '2025-05-27 01:09:01', 'Initial status for application 14'),
(15, 15, 'Submitted', 15, '2025-05-27 01:09:01', 'Initial status for application 15'),
(16, 16, 'Submitted', 16, '2025-05-27 01:09:01', 'Initial status for application 16'),
(17, 17, 'Submitted', 17, '2025-05-27 01:09:01', 'Initial status for application 17'),
(18, 18, 'Submitted', 18, '2025-05-27 01:09:01', 'Initial status for application 18'),
(19, 19, 'Submitted', 19, '2025-05-27 01:09:01', 'Initial status for application 19'),
(20, 20, 'Submitted', 20, '2025-05-27 01:09:01', 'Initial status for application 20'),
(21, 21, 'Submitted', 21, '2025-05-27 01:09:01', 'Initial status for application 21'),
(22, 22, 'Submitted', 22, '2025-05-27 01:09:01', 'Initial status for application 22'),
(23, 23, 'Submitted', 23, '2025-05-27 01:09:01', 'Initial status for application 23'),
(24, 24, 'Submitted', 24, '2025-05-27 01:09:01', 'Initial status for application 24');

INSERT INTO approved (id, application_id, approval_date, approval_notes, interest_rate, term_years)
VALUES
(5, 5, '2024-11-01', 'Approved notes for app 5', 6.5, 30),
(6, 6, '2024-11-02', 'Approved notes for app 6', 6.5, 30),
(7, 7, '2024-11-03', 'Approved notes for app 7', 6.5, 30),
(8, 8, '2024-11-04', 'Approved notes for app 8', 6.5, 30),
(9, 9, '2024-11-05', 'Approved notes for app 9', 6.5, 30),
(10, 10, '2024-11-06', 'Approved notes for app 10', 6.5, 30),
(11, 11, '2024-11-07', 'Approved notes for app 11', 6.5, 30),
(12, 12, '2024-11-08', 'Approved notes for app 12', 6.5, 30),
(13, 13, '2024-11-09', 'Approved notes for app 13', 6.5, 30),
(14, 14, '2024-11-10', 'Approved notes for app 14', 6.5, 30),
(15, 15, '2024-11-11', 'Approved notes for app 15', 6.5, 30),
(16, 16, '2024-11-12', 'Approved notes for app 16', 6.5, 30),
(17, 17, '2024-11-13', 'Approved notes for app 17', 6.5, 30),
(18, 18, '2024-11-14', 'Approved notes for app 18', 6.5, 30),
(19, 19, '2024-11-15', 'Approved notes for app 19', 6.5, 30),
(20, 20, '2024-11-16', 'Approved notes for app 20', 6.5, 30),
(21, 21, '2024-11-17', 'Approved notes for app 21', 6.5, 30),
(22, 22, '2024-11-18', 'Approved notes for app 22', 6.5, 30),
(23, 23, '2024-11-19', 'Approved notes for app 23', 6.5, 30),
(24, 24, '2024-11-20', 'Approved notes for app 24', 6.5, 30);

INSERT INTO closed (id, application_id, closing_date, settlement_agent)
VALUES
(5, 5, '2024-12-01', 'Agent 5'),
(6, 6, '2024-12-02', 'Agent 6'),
(7, 7, '2024-12-03', 'Agent 7'),
(8, 8, '2024-12-04', 'Agent 8'),
(9, 9, '2024-12-05', 'Agent 9'),
(10, 10, '2024-12-06', 'Agent 10'),
(11, 11, '2024-12-07', 'Agent 11'),
(12, 12, '2024-12-08', 'Agent 12'),
(13, 13, '2024-12-09', 'Agent 13'),
(14, 14, '2024-12-10', 'Agent 14'),
(15, 15, '2024-12-11', 'Agent 15'),
(16, 16, '2024-12-12', 'Agent 16'),
(17, 17, '2024-12-13', 'Agent 17'),
(18, 18, '2024-12-14', 'Agent 18'),
(19, 19, '2024-12-15', 'Agent 19'),
(20, 20, '2024-12-16', 'Agent 20'),
(21, 21, '2024-12-17', 'Agent 21'),
(22, 22, '2024-12-18', 'Agent 22'),
(23, 23, '2024-12-19', 'Agent 23'),
(24, 24, '2024-12-20', 'Agent 24');

INSERT INTO declined (id, application_id, declined_date, reason)
VALUES
(5, 5, '2024-10-01', 'Declined reason for app 5'),
(6, 6, '2024-10-02', 'Declined reason for app 6'),
(7, 7, '2024-10-03', 'Declined reason for app 7'),
(8, 8, '2024-10-04', 'Declined reason for app 8'),
(9, 9, '2024-10-05', 'Declined reason for app 9'),
(10, 10, '2024-10-06', 'Declined reason for app 10'),
(11, 11, '2024-10-07', 'Declined reason for app 11'),
(12, 12, '2024-10-08', 'Declined reason for app 12'),
(13, 13, '2024-10-09', 'Declined reason for app 13'),
(14, 14, '2024-10-10', 'Declined reason for app 14'),
(15, 15, '2024-10-11', 'Declined reason for app 15'),
(16, 16, '2024-10-12', 'Declined reason for app 16'),
(17, 17, '2024-10-13', 'Declined reason for app 17'),
(18, 18, '2024-10-14', 'Declined reason for app 18'),
(19, 19, '2024-10-15', 'Declined reason for app 19'),
(20, 20, '2024-10-16', 'Declined reason for app 20'),
(21, 21, '2024-10-17', 'Declined reason for app 21'),
(22, 22, '2024-10-18', 'Declined reason for app 22'),
(23, 23, '2024-10-19', 'Declined reason for app 23'),
(24, 24, '2024-10-20', 'Declined reason for app 24');

INSERT INTO funded (id, application_id, funded_date, wire_amount, wire_confirmation)
VALUES
(5, 5, '2025-01-01', 255000, 'CONFIRM5'),
(6, 6, '2025-01-02', 256000, 'CONFIRM6'),
(7, 7, '2025-01-03', 257000, 'CONFIRM7'),
(8, 8, '2025-01-04', 258000, 'CONFIRM8'),
(9, 9, '2025-01-05', 259000, 'CONFIRM9'),
(10, 10, '2025-01-06', 260000, 'CONFIRM10'),
(11, 11, '2025-01-07', 261000, 'CONFIRM11'),
(12, 12, '2025-01-08', 262000, 'CONFIRM12'),
(13, 13, '2025-01-09', 263000, 'CONFIRM13'),
(14, 14, '2025-01-10', 264000, 'CONFIRM14'),
(15, 15, '2025-01-11', 265000, 'CONFIRM15'),
(16, 16, '2025-01-12', 266000, 'CONFIRM16'),
(17, 17, '2025-01-13', 267000, 'CONFIRM17'),
(18, 18, '2025-01-14', 268000, 'CONFIRM18'),
(19, 19, '2025-01-15', 269000, 'CONFIRM19'),
(20, 20, '2025-01-16', 270000, 'CONFIRM20'),
(21, 21, '2025-01-17', 271000, 'CONFIRM21'),
(22, 22, '2025-01-18', 272000, 'CONFIRM22'),
(23, 23, '2025-01-19', 273000, 'CONFIRM23'),
(24, 24, '2025-01-20', 274000, 'CONFIRM24');

INSERT INTO documentation (id, application_id, doc_type, file_path, uploaded_at, uploaded_by)
VALUES
(5, 5, 'W-2', '/files/docs/w2_5.pdf', '2024-10-01 10:00:00', 'Uploader5'),
(6, 6, 'W-2', '/files/docs/w2_6.pdf', '2024-10-01 10:00:00', 'Uploader6'),
(7, 7, 'W-2', '/files/docs/w2_7.pdf', '2024-10-01 10:00:00', 'Uploader7'),
(8, 8, 'W-2', '/files/docs/w2_8.pdf', '2024-10-01 10:00:00', 'Uploader8'),
(9, 9, 'W-2', '/files/docs/w2_9.pdf', '2024-10-01 10:00:00', 'Uploader9'),
(10, 10, 'W-2', '/files/docs/w2_10.pdf', '2024-10-01 10:00:00', 'Uploader10'),
(11, 11, 'W-2', '/files/docs/w2_11.pdf', '2024-10-01 10:00:00', 'Uploader11'),
(12, 12, 'W-2', '/files/docs/w2_12.pdf', '2024-10-01 10:00:00', 'Uploader12'),
(13, 13, 'W-2', '/files/docs/w2_13.pdf', '2024-10-01 10:00:00', 'Uploader13'),
(14, 14, 'W-2', '/files/docs/w2_14.pdf', '2024-10-01 10:00:00', 'Uploader14'),
(15, 15, 'W-2', '/files/docs/w2_15.pdf', '2024-10-01 10:00:00', 'Uploader15'),
(16, 16, 'W-2', '/files/docs/w2_16.pdf', '2024-10-01 10:00:00', 'Uploader16'),
(17, 17, 'W-2', '/files/docs/w2_17.pdf', '2024-10-01 10:00:00', 'Uploader17'),
(18, 18, 'W-2', '/files/docs/w2_18.pdf', '2024-10-01 10:00:00', 'Uploader18'),
(19, 19, 'W-2', '/files/docs/w2_19.pdf', '2024-10-01 10:00:00', 'Uploader19'),
(20, 20, 'W-2', '/files/docs/w2_20.pdf', '2024-10-01 10:00:00', 'Uploader20'),
(21, 21, 'W-2', '/files/docs/w2_21.pdf', '2024-10-01 10:00:00', 'Uploader21'),
(22, 22, 'W-2', '/files/docs/w2_22.pdf', '2024-10-01 10:00:00', 'Uploader22'),
(23, 23, 'W-2', '/files/docs/w2_23.pdf', '2024-10-01 10:00:00', 'Uploader23'),
(24, 24, 'W-2', '/files/docs/w2_24.pdf', '2024-10-01 10:00:00', 'Uploader24');
